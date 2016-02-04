package org.mystic

import java.io.PrintWriter
import java.net.{Authenticator, PasswordAuthentication}
import java.util.Scanner

import org.joda.time.format.{DateTimeFormat, DateTimeFormatterBuilder}
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.mystic.Template.MultiHashSet


object DotaBuffCall {

  val userNames = List("LeCassette", "Paul", "Grace", "Ice-CreaM^", "Zlodey", "JK", "Klydd", "P11", "Gwaed Oer", "improve", "RALFGTS")

  val wins = new MultiHashSet[String]
  val losses = new MultiHashSet[String]

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


  def printSet(set: MultiHashSet[String]) = {
    set.map.keySet.foreach(key => println(s"$key ${set.count(key)}"))
  }

  def main(args: Array[String]): Unit = {
    val in = new Scanner(System.in)
    val n = in.nextInt()
    in.nextLine()
    val out = new PrintWriter("advanced-stats.txt")
    for (i <- 0 until n) {
      val name = in.nextLine()
      val doc = Jsoup.connect(name)
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .get()
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
        DateTimeFormat.forPattern("mm:ss").getParser()
      )
      val formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter()
      var duration = 0
      try {
        duration = (formatter.parseLocalTime(children.get(4 + ind).child(0).text()).getMillisOfDay / 1000)
      } catch {
        case e: Exception => {
          println(children.get(4).child(0).text())
          println(name)
        }
      }
      val teamsResults = doc.select("div[class*=team-results")
      // radiant
      val radiantResults: Element = teamsResults.first.children().get(0)
      val direResults: Element = teamsResults.first.children().get(1)
      //      if (gameMode.equalsIgnoreCase("Captains Mode")) {
      //        // todo get statistics about bans
      //        direResults = teamsResults.first.children().get(2)
      //        val radiantBans = teamsResults.first.children().get(0)
      //        val direBans = teamsResults.first.children().last()
      //      }
      var ourTeam = ""
      if (!userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).isEmpty) {
        ourTeam = "radiant"
      } else {
        ourTeam = "dire"
      }
      val numberOfPlayers = userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).size + userNames.filter(name => direResults.text.toLowerCase.contains(name.toLowerCase)).size
      addWinsAndLosses(findOurTeam(radiantResults, direResults, ourTeam), winOrLoss(ourTeam, win))
      out.println(s"${printWin(ourTeam, win)}\t${printGameMode(gameMode)}\t$lobbyType\t$skillBracket\t$numberOfPlayers\t$duration\t$region")
      out.flush()
      val teamAbilityBuilds = doc.select("div[class*=match-ability-builds")
    }
    printSet(losses)
    println("--------")
    printSet(wins)
    out.close()
  }
}
