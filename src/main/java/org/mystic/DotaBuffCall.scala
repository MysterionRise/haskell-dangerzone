package org.mystic

import java.io.PrintWriter
import java.net.{Authenticator, PasswordAuthentication}
import java.util
import java.util.concurrent.TimeUnit

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatterBuilder}
import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, TextNode}
import org.jsoup.select.Elements
import org.mystic.Template.MultiHashSet

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object DotaBuffCall {

  setupProxy

  val out = new PrintWriter("advanced-stats.txt")

  //"238193250" - out of scope, strange bug with matches
  // TODO add possibility to get name by id, to prevent renaming issues
  val users = List(
    "112427189",
    "116894024",
    "92253911",
    "224417226",
    //"120301254", // cableman
    "165818364",
    "133208812",
    "42344936",
    "141246748",
    "98365520",
    "118794347",
    "117746168",
    "248823035",
    "302522553")

  val userNames = users.map(getUserName)

  val wins = new MultiHashSet[String]
  val losses = new MultiHashSet[String]
  val cmPicks = new MultiHashSet[String]
  val cmBans = new MultiHashSet[String]
  val otherModePicks = new MultiHashSet[String]

  def getUserName(userID: String): String = {
    try {
      val doc = Jsoup.connect(s"http://www.dotabuff.com/players/$userID/")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      TimeUnit.SECONDS.sleep(10)
      val userName: TextNode = doc.getElementsByClass("header-content-title").get(0).childNode(0).childNode(0).asInstanceOf[TextNode]
      userName.text()
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)
        println(s"http://www.dotabuff.com/players/$userID/")
      }
        // return userId if some error happens
        userID
    }
  }

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
        TimeUnit.SECONDS.sleep(10)
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
    userNames.filter(name => ourTeam.text.toLowerCase.contains(name.toLowerCase)).foreach(name =>
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
    set.map.keySet.foreach(key => out.println(s"$key\t${set.count(key)}"))
  }

  def addBansAndPicks(ourTeam: Elements) = {
    for (i <- 0 until ourTeam.size()) {
      val fullHero: String = ourTeam.get(i).children().first().children().first().children().attr("href")
      val ind = fullHero.lastIndexOf("/") + 1
      i match {
        case 0 | 1 | 4 | 5 | 8 => cmBans.add(fullHero.substring(ind))
        case _ => otherModePicks.add(fullHero.substring(ind))
      }
    }
  }


  def addPicks(ourTeam: Elements) = {
    for (i <- 0 until ourTeam.size()) {
      val fullHero = ourTeam.get(i).children()
      val userName = fullHero.get(1).text() // username
      val heroName = fullHero.first().children().first().children().first().attr("href").substring(8) // hero name
      if (userNames.contains(userName)) {
        otherModePicks.add(heroName)
      }
    }
  }

  def main(args: Array[String]): Unit = {
//    val allMatches = users.map(getAllMatches)
//    val combinedMatches = new mutable.HashSet[String]
//    for (i <- 0 until allMatches.size) {
//      for (j <- i + 1 until allMatches.size) {
//        combinedMatches ++= allMatches(i).intersect(allMatches(j))
//      }
//    }
    val size = StdIn.readInt()
    val combined = new util.ArrayList[String]()
    for (i <- 0 until size) {
      combined.add(StdIn.readLine())
    }
    combined.toArray(new Array[String](0)).foreach(name => {
      val doc = Jsoup.connect(s"$name")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      TimeUnit.SECONDS.sleep(10)
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
//        size += 1
        val teamsResults = doc.select("div[class*=team-results")
        // radiant
        val radiantResults: Element = teamsResults.first.children().get(0)
        val direResults: Element = teamsResults.first.children().get(1)
        var ourTeam = ""
        if (!userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).isEmpty) {
          ourTeam = "radiant"
        } else {
          ourTeam = "dire"
        }
        if (gameMode.equalsIgnoreCase("Captains Mode")) {
          // todo get statistics about bans
          val radiantBans = teamsResults.first.children().get(0).children().get(2).children().get(0).children()
          val direBans = teamsResults.first.children().get(1).children().get(2).children().get(0).children()
          addBansAndPicks(findOurTeam(radiantBans, direBans, ourTeam))
        } else {
          val radiantHeroes = teamsResults.first.children().get(0).children().get(1).children().get(0).children().get(1).children()
          val direHeroes = teamsResults.first.children().get(1).children().get(1).children().get(0).children().get(1).children()
          addPicks(findOurTeam(radiantHeroes, direHeroes, ourTeam))
        }

        val numberOfPlayers = userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).size + userNames.filter(name => direResults.text.toLowerCase.contains(name.toLowerCase)).size
        addWinsAndLosses(findOurTeam(radiantResults, direResults, ourTeam), winOrLoss(ourTeam, win))
        out.println(s"${printWin(ourTeam, win)}\t${printGameMode(gameMode)}\t$lobbyType\t$skillBracket\t$numberOfPlayers\t$duration\t$region\t${date.toString("dd-mm-yyyy")}")
        out.flush()
        val teamAbilityBuilds = doc.select("div[class*=match-ability-builds")
      }
    })
    out.println(size)
    out.println("---losses-----")
    printSet(losses)
    out.println("---wins-----")
    printSet(wins)
    out.println("----CM-picks----")
    printSet(cmPicks)
    out.println("---CM-bans-----")
    printSet(cmBans)
    out.println("----other-mode-picks----")
    printSet(otherModePicks)
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
