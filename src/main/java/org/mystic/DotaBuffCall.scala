package org.mystic

import java.io.PrintWriter
import java.net.{Authenticator, PasswordAuthentication}
import java.util

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatterBuilder}
import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, TextNode}
import org.jsoup.select.Elements
import org.mystic.Template.MultiHashSet

import scala.collection.mutable
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

  val teams = List(
    "111474", //Alliance
    "1838315", //Team Secret
    "39", //EG
    "2586976", //OG
    "36", //Navi
    "2163", // Liquid
    "1148284", // MVP.P
    "350190", // Fnatic
    "1375614", // Newbee
    "15", // LGD
    "2777247", // VGR
    "46", // Empire
    "543897", // Mineski
    "3", // cOL
    "1836806", // wings
    "2512249", // DC
    // not Manila teams

    "2621843", //Team Spirit
    "2006913", //Vega
    "1883502", //VP
    "4", //EHOME
    "726228", //VG
    "5", //IG
    "2635099", //CDEC.Y
    "1520578", //CDEC
    "2642171" //Ad Finem
  )

  //  val teamNames = teams.map(getUserName("http://www.dotabuff.com/esports/teams", _))
  val userNames = List("123")
  //  val userNames = users.map(getUserName)

  val wins = new MultiHashSet[String]
  val losses = new MultiHashSet[String]
  val cmPicks = new MultiHashSet[String]
  val cmBans = new MultiHashSet[String]
  val otherModePicks = new MultiHashSet[String]

  def getUserName(id: String): String = {
    getUserName("http://www.dotabuff.com/players", id)
  }

  def getUserName(baseEndpont: String, id: String): String = {
    try {
      val doc = Jsoup.connect(s"$baseEndpont/$id/")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      Thread.sleep(10000)
      val userName: TextNode = doc.getElementsByClass("header-content-title").get(0).childNode(0).childNode(0).asInstanceOf[TextNode]
      userName.text()
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)
        println(s"http://www.dotabuff.com/players/$id/")
      }
        // return userId if some error happens
        id
    }
  }

  def getAllMatches(baseEndpoint: String, id: String): Set[String] = {
    val results = new ArrayBuffer[String]()
    var totalMatches = 0
    var page = 1
    var added = 1
    while (page < 3) {
      //    while (added > 0) {
      try {
        val doc = Jsoup.connect(s"$baseEndpoint/$id/matches?page=$page")
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
          println(s"$baseEndpoint/$id/matches?page=$page")
        }
      }
    }
    println(totalMatches)
    results.toSet
  }

  def getAllMatchesForTeam(teamID: String): Set[String] = {
    getAllMatches("http://www.dotabuff.com/esports/teams", teamID)
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

  /**
    * ["\/matches\/2359311428", "\/matches\/2379473270", "\/matches\/2383834015", "\/matches\/2357638993", "\/matches\/2374089029", "\/matches\/2352938620", "\/matches\/2353167913", "\/matches\/2361906123", "\/matches\/2385353363", "\/matches\/2355894068", "\/matches\/2387450856", "\/matches\/2298421256", "\/matches\/2327418889", "\/matches\/2387680209", "\/matches\/2381687924", "\/matches\/2381980118", "\/matches\/2298713609", "\/matches\/2298565632", "\/matches\/2362849384", "\/matches\/2379622728", "\/matches\/2365829219", "\/matches\/2381831351", "\/matches\/2296159612", "\/matches\/2353646896", "\/matches\/2326609006", "\/matches\/2352859075", "\/matches\/2365584366", "\/matches\/2353764925", "\/matches\/2387912246", "\/matches\/2387598938", "\/matches\/2359431599", "\/matches\/2379320481", "\/matches\/2296020893", "\/matches\/2365714841", "\/matches\/2362315882", "\/matches\/2374230684", "\/matches\/2355766229", "\/matches\/2365455182", "\/matches\/2365122870", "\/matches\/2362110197", "\/matches\/2373452678", "\/matches\/2389863725", "\/matches\/2386118329", "\/matches\/2362722554", "\/matches\/2359544019", "\/matches\/2360573746", "\/matches\/2353463767", "\/matches\/2360795429", "\/matches\/2365934194", "\/matches\/2327527625", "\/matches\/2361741329", "\/matches\/2387375650", "\/matches\/2358060936", "\/matches\/2360692445", "\/matches\/2388037202", "\/matches\/2385563388", "\/matches\/2364934706", "\/matches\/2354005458", "\/matches\/2358275776", "\/matches\/2358375544", "\/matches\/2357832186", "\/matches\/2383979763", "\/matches\/2389940352", "\/matches\/2353901277", "\/matches\/2364761262", "\/matches\/2373343805"]

    */

  def main(args: Array[String]) {
    //    teamNames.foreach(println(_))
    //    println(scala.util.parsing.json.JSONArray(teamNames))
    //    val allMatches = teams.map(getAllMatchesForTeam)
    //    val combinedMatches = new mutable.HashSet[String]
    //    for (i <- 0 until allMatches.size) {
    //      for (j <- i + 1 until allMatches.size) {
    //        combinedMatches ++= allMatches(i).intersect(allMatches(j))
    //      }
    //    }
    //    println(combinedMatches.size)
    //    println(scala.util.parsing.json.JSONArray(combinedMatches.toList))
    val p = scala.util.parsing.json.JSON.parseFull(readLine()).get.asInstanceOf[List[String]]
    p.foreach(game => {

    })
    println()
  }

  //  def main(args: Array[String]): Unit = {
  ////    val allMatches = users.map(getAllMatches)
  ////    val combinedMatches = new mutable.HashSet[String]
  ////    for (i <- 0 until allMatches.size) {
  ////      for (j <- i + 1 until allMatches.size) {
  ////        combinedMatches ++= allMatches(i).intersect(allMatches(j))
  ////      }
  ////    }
  //    val size = StdIn.readInt()
  //    val combined = new util.ArrayList[String]()
  //    for (i <- 0 until size) {
  //      combined.add(StdIn.readLine())
  //    }
  //    combined.toArray(new Array[String](0)).foreach(name => {
  //      val doc = Jsoup.connect(s"$name")
  //        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
  //        .timeout(0)
  //        .get()
  //      Thread.sleep(10000)
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
  //        DateTimeFormat.forPattern("mm:ss").getParser(),
  //        DateTimeFormat.forPattern("yyyy-mm-dd").getParser()
  //      )
  //      val formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter()
  //      var duration = 0
  //      var date: DateTime = null
  //      try {
  //        duration = (formatter.parseLocalTime(children.get(4 + ind).child(0).text()).getMillisOfDay / 1000)
  //        date = formatter.parseDateTime(children.get(5 + ind).child(0).text())
  //      } catch {
  //        case e: Exception => {
  //          println(children.get(4).child(0).text())
  //          println(children.get(5).child(0).text())
  //          println(name)
  //        }
  //      }
  //      if (!date.minusYears(2016).toString().startsWith("-")) {
  //        println(s"http://www.dotabuff.com$name")
  ////        size += 1
  //        val teamsResults = doc.select("div[class*=team-results")
  //        // radiant
  //        val radiantResults: Element = teamsResults.first.children().get(0)
  //        val direResults: Element = teamsResults.first.children().get(1)
  //        var ourTeam = ""
  //        if (!userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).isEmpty) {
  //          ourTeam = "radiant"
  //        } else {
  //          ourTeam = "dire"
  //        }
  //        if (gameMode.equalsIgnoreCase("Captains Mode")) {
  //          // todo get statistics about bans
  //          val radiantBans = teamsResults.first.children().get(0).children().get(2).children().get(0).children()
  //          val direBans = teamsResults.first.children().get(1).children().get(2).children().get(0).children()
  //          addBansAndPicks(findOurTeam(radiantBans, direBans, ourTeam))
  //        } else {
  //          val radiantHeroes = teamsResults.first.children().get(0).children().get(1).children().get(0).children().get(1).children()
  //          val direHeroes = teamsResults.first.children().get(1).children().get(1).children().get(0).children().get(1).children()
  //          addPicks(findOurTeam(radiantHeroes, direHeroes, ourTeam))
  //        }
  //
  //        val numberOfPlayers = userNames.filter(name => radiantResults.text.toLowerCase.contains(name.toLowerCase)).size + userNames.filter(name => direResults.text.toLowerCase.contains(name.toLowerCase)).size
  //        addWinsAndLosses(findOurTeam(radiantResults, direResults, ourTeam), winOrLoss(ourTeam, win))
  //        out.println(s"${printWin(ourTeam, win)}\t${printGameMode(gameMode)}\t$lobbyType\t$skillBracket\t$numberOfPlayers\t$duration\t$region\t${date.toString("dd-mm-yyyy")}")
  //        out.flush()
  //        val teamAbilityBuilds = doc.select("div[class*=match-ability-builds")
  //      }
  //    })
  //    out.println(size)
  //    out.println("---losses-----")
  //    printSet(losses)
  //    out.println("---wins-----")
  //    printSet(wins)
  //    out.println("----CM-picks----")
  //    printSet(cmPicks)
  //    out.println("---CM-bans-----")
  //    printSet(cmBans)
  //    out.println("----other-mode-picks----")
  //    printSet(otherModePicks)
  //    out.close()
  //  }

  def updateRating(oldRating: Int, K: Double, w: Double, we: Double) = oldRating + K * (w - we)

  def calcWE(diffRating: Int) = 1f / (Math.pow(10, (-diffRating / 400)) + 1)

  def probabilityOfWin(ratingA: Int, ratingB: Int) = calcWE(ratingA - ratingB)

  def margin(pointDiff: Int, elow: Int, elol: Int): Double = Math.log(Math.abs(pointDiff) + 1) * (2.2f / ((elow - elol) * 0.0001f + 2.2f))

  /**
    * @param K should be different for different leagues
    *          soccer example:
    *          60 for World Cup finals;
    *          50 for continental championship finals and major intercontinental tournaments;
    *          40 for World Cup and continental qualifiers and major tournaments;
    *          30 for all other tournaments;
    *          20 for friendly matches.
    */
  def updateRatingWithMargin(oldRating: Int, K: Int, w: Float, we: Float, elow: Int, elol: Int, pointDiff: Int) = updateRating(oldRating, margin(pointDiff, elow, elol) * K, w, we)

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
