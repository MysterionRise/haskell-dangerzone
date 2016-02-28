package org.mystic

import java.io.PrintWriter
import java.net.{PasswordAuthentication, Authenticator}

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatterBuilder, DateTimeFormat}
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.mystic.Template.MultiHashSet

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object DotaBuffCall {

  //"238193250" - out of scope, strange bug with matches
  // TODO add possibility to get name by id, to prevent renaming issues
  val users = Map(
    "112427189" -> "LilAngryBoy",
    "116894024" -> "LeCassette",
    "92253911" -> "Grace",
    "224417226" -> "Paul",
    "120301254" -> "Cableman",
    "165818364" -> "JK",
    "133208812" -> "Klydd",
    "42344936" -> "improve",
    "141246748" -> "Ice-CreaM^",
    "98365520" -> "P11",
    "118794347" -> "Gwaed Oer",
    "117746168" -> "RALFGTS",
    "248823035" -> "Zlodey")

  val wins = new MultiHashSet[String]
  val losses = new MultiHashSet[String]
  val cmPicks = new MultiHashSet[String]
  val cmBans = new MultiHashSet[String]

  def getAllMatches(userID: String): Set[String] = {
    val results = new ArrayBuffer[String]()
    var totalMatches = 0
    var page = 1
    var added = 1
    while (added > 0) {
      try {
        val doc = Jsoup.connect(s"http://www.dotabuff.com/players/$userID/matches?page=$page")
          .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
          .timeout(0)
          .get()
        Thread.sleep(10000)
        val wonGames = doc.getElementsByClass("won")
        val lostGames = doc.getElementsByClass("lost")
        val abandonedGames = doc.getElementsByClass("abandoned")
        for (i <- 0 until wonGames.size()) {
          results.append(wonGames.get(i).attr("href"))
        }
        for (i <- 0 until lostGames.size()) {
          results.append(lostGames.get(i).attr("href"))
        }
        for (i <- 0 until abandonedGames.size()) {
          results.append(abandonedGames.get(i).attr("href"))
        }
        added = wonGames.size + lostGames.size + abandonedGames.size
        println(results.size)
        totalMatches += added
        page += 1
      } catch {
        case e: Exception => {
          e.printStackTrace(System.out)
          println(s"http://www.dotabuff.com/players/$userID/matches?page=$page")
        }
      }
    }
    println(totalMatches)
    results.toSet
  }

  def printWin(ourTeam: String, win: String) = if (winOrLoss(ourTeam, win)) "W" else "L"

  def winOrLoss(ourTeam: String, win: String) = ourTeam.equalsIgnoreCase(win)

  def printGameMode(gameMode: String) = gameMode.split(" ").map(mode => mode.charAt(0)).mkString

  def addWinsAndLosses(ourTeam: Element, win: Boolean) = {
    users.values.filter(name => ourTeam.text.toLowerCase.contains(name.toLowerCase)).foreach(name =>
      if (win) wins.add(name) else losses.add(name))
  }

  def findOurTeam(radiantResults: Element, direResults: Element, ourTeam: String): Element = ourTeam match {
    case "dire" => direResults
    case "radiant" => radiantResults
  }

  def findOurTeam(radiantBans: Elements, direBans: Elements, ourTeam: String) =
    ourTeam match {
      case "dire" => direBans
      case "radiant" => radiantBans
    }

  def printSet(set: MultiHashSet[String]) = {
    set.map.keySet.foreach(key => println(s"$key ${
      set.count(key)
    }"))
  }

  def addBansAndPicks(ourTeam: Elements) = {
    for (i <- 0 until ourTeam.size()) {
      val fullHero: String = ourTeam.get(i).children().first().children().first().children().attr("href")
      val ind = fullHero.lastIndexOf("/") + 1
      i match {
        case 0 | 1 | 4 | 5 | 8 => cmBans.add(fullHero.substring(ind))
        case _ => cmPicks.add(fullHero.substring(ind))
      }
    }
  }


  def main(args: Array[String]): Unit = {
    setupProxy
    val allMatches = users.keySet.map(getAllMatches).toList
    val combinedMatches = new mutable.HashSet[String]
    for (i <- 0 until allMatches.size) {
      for (j <- i + 1 until allMatches.size) {
        combinedMatches ++= allMatches(i).intersect(allMatches(j))
      }
    }
    //    println(combinedMatches.size)
    //    combinedMatches.foreach(name => println(s"http://www.dotabuff.com$name"))
    val n = combinedMatches.size
    val out = new PrintWriter("advanced-stats.txt")
    var size = 0
    combinedMatches.foreach(name => {
      val doc = Jsoup.connect(s"http://www.dotabuff.com$name")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      Thread.sleep(10000)
      // who win the game
      var win = ""
      val dire: Elements = doc.select("div[class*=match-result team dire")
      val radiant: Elements = doc.select("div[class*=match-result team radiant")
      if (winOrLoss(dire.text().trim, "Dire Victory")) {
        win = "dire"
      } else {
        win = "radiant"
      }
      val gameResults: Elements = doc.select("div[class*=header-content-secondary]")
      val children = gameResults.iterator().next().children()
      var ind = 0
      var skillBracket = "-"
      if (children.size() == 5) {
        ind = -1
      } else {
        skillBracket = children.get(0).child(0).text()
      }
      val lobbyType = children.get(1 + ind).child(0).text()
      val gameMode = children.get(2 + ind).child(0).text()
      val region = children.get(3 + ind).child(0).text()
      val parsers = Array(
        DateTimeFormat.forPattern("hh:mm:ss").getParser(),
        DateTimeFormat.forPattern("mm:ss").getParser(),
        DateTimeFormat.forPattern("yyyy-mm-dd").getParser()
      )
      val formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter()
      var duration = 0
      var date: DateTime = null
      try {
        duration = (formatter.parseLocalTime(children.get(4 + ind).child(0).text()).getMillisOfDay / 1000)
        date = formatter.parseDateTime(children.get(5 + ind).child(0).text())
      } catch {
        case e: Exception => {
          println(children.get(4).child(0).text())
          println(children.get(5).child(0).text())
          println(name)
        }
      }
      if (!date.minusYears(2016).toString().startsWith("-")) {
        println(s"http://www.dotabuff.com$name")
        size += 1
        val teamsResults = doc.select("div[class*=team-results")
        // radiant
        val radiantResults: Element = teamsResults.first.children().get(0)
        val direResults: Element = teamsResults.first.children().get(1)
        var ourTeam = ""
        if (!users.values.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).isEmpty) {
          ourTeam = "radiant"
        } else {
          ourTeam = "dire"
        }
        if (gameMode.equalsIgnoreCase("Captains Mode")) {
          // todo get statistics about bans
          val radiantBans = teamsResults.first.children().get(0).children().get(2).children().get(0).children()
          val direBans = teamsResults.first.children().get(1).children().get(2).children().get(0).children()
          addBansAndPicks(findOurTeam(radiantBans, direBans, ourTeam))
        }

        val numberOfPlayers = users.values.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).size + users.values.filter(name => direResults.text.toLowerCase.contains(name.toLowerCase)).size
        addWinsAndLosses(findOurTeam(radiantResults, direResults, ourTeam), winOrLoss(ourTeam, win))
        out.println(s"${printWin(ourTeam, win)}\t${printGameMode(gameMode)}\t$lobbyType\t$skillBracket\t$numberOfPlayers\t$duration\t$region\t${date.toString("dd-mm-yyyy")}")
        out.flush()
        val teamAbilityBuilds = doc.select("div[class*=match-ability-builds")
      }
    })
    println(size)
    printSet(losses)
    println("--------")
    printSet(wins)
    println("--------")
    printSet(cmPicks)
    println("--------")
    printSet(cmBans)
    out.close()
  }

  def setupProxy: String = {
    val authUser = "-"
    val authPassword = "-"
    val authenticator: Authenticator = new Authenticator {
      override def getPasswordAuthentication: PasswordAuthentication = {
        return new PasswordAuthentication(authUser, authPassword.toCharArray())
      }
    }
    Authenticator.setDefault(authenticator)
    System.setProperty("http.proxyHost", "-")
    System.setProperty("http.proxyPort", "-")
  }
}
