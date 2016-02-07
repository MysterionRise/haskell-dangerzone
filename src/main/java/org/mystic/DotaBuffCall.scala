package org.mystic

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.mystic.Template.MultiHashSet

import scala.collection.mutable.ArrayBuffer

object DotaBuffCall {

  val userNames = List("LeCassette", "Paul", "Grace", "Ice-CreaM^", "Zlodey", "JK", "Klydd", "P11", "Gwaed Oer", "improve", "RALFGTS", "LilAngryBoy")
  val userIDs = List("112427189", "116894024", "92253911", "224417226", "120301254", "165818364", "133208812", "238193250", "42344936", "141246748",
    "98365520", "118794347", "117746168", "248823035")

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
      val doc = Jsoup.connect(s"http://www.dotabuff.com/players/$userID/matches?page=$page")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .get()
      Thread.sleep(15000)
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
    val allMatches = userIDs.map(getAllMatches(_))
    println(allMatches.size)
    //    val in = new Scanner(System.in)
    //    val n = in.nextInt()
    //    in.nextLine()
    //    val out = new PrintWriter("advanced-stats.txt")
    //    for (i <- 0 until n) {
    //      val name = in.nextLine()
    //      val doc = Jsoup.connect(name)
    //        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
    //        .get()
    //      // who win the game
    //      var win = ""
    //      val dire: Elements = doc.select("div[class*=match-result team dire")
    //      val radiant: Elements = doc.select("div[class*=match-result team radiant")
    //      if (winOrLoss(dire.text().trim, "Dire Victory")) {
    //        win = "dire"
    //      } else {
    //        win = "radiant"
    //      }
    //      val gameResults: Elements = doc.select("div[class*=header-content-secondary]")
    //      val children = gameResults.iterator().next().children()
    //      var ind = 0
    //      var skillBracket = "-"
    //      if (children.size() == 5) {
    //        ind = -1
    //      } else {
    //        skillBracket = children.get(0).child(0).text()
    //      }
    //      val lobbyType = children.get(1 + ind).child(0).text()
    //      val gameMode = children.get(2 + ind).child(0).text()
    //      val region = children.get(3 + ind).child(0).text()
    //      val parsers = Array(
    //        DateTimeFormat.forPattern("hh:mm:ss").getParser(),
    //        DateTimeFormat.forPattern("mm:ss").getParser()
    //      )
    //      val formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter()
    //      var duration = 0
    //      try {
    //        duration = (formatter.parseLocalTime(children.get(4 + ind).child(0).text()).getMillisOfDay / 1000)
    //      } catch {
    //        case e: Exception => {
    //          println(children.get(4).child(0).text())
    //          println(name)
    //        }
    //      }
    //      val teamsResults = doc.select("div[class*=team-results")
    //      // radiant
    //      val radiantResults: Element = teamsResults.first.children().get(0)
    //      val direResults: Element = teamsResults.first.children().get(1)
    //      var ourTeam = ""
    //      if (!userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).isEmpty) {
    //        ourTeam = "radiant"
    //      } else {
    //        ourTeam = "dire"
    //      }
    //      if (gameMode.equalsIgnoreCase("Captains Mode")) {
    //        // todo get statistics about bans
    //        val radiantBans = teamsResults.first.children().get(0).children().get(2).children().get(0).children()
    //        val direBans = teamsResults.first.children().get(1).children().get(2).children().get(0).children()
    //        addBansAndPicks(findOurTeam(radiantBans, direBans, ourTeam))
    //      }
    //
    //      val numberOfPlayers = userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).size + userNames.filter(name => direResults.text.toLowerCase.contains(name.toLowerCase)).size
    //      addWinsAndLosses(findOurTeam(radiantResults, direResults, ourTeam), winOrLoss(ourTeam, win))
    //      out.println(s"${printWin(ourTeam, win)}\t${printGameMode(gameMode)}\t$lobbyType\t$skillBracket\t$numberOfPlayers\t$duration\t$region")
    //      out.flush()
    //      val teamAbilityBuilds = doc.select("div[class*=match-ability-builds")
    //    }
    //    printSet(losses)
    //    println("--------")
    //    printSet(wins)
    //    println("--------")
    //    printSet(cmPicks)
    //    println("--------")
    //    printSet(cmBans)
    //    out.close()
  }
}
