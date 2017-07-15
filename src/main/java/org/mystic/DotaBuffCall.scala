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

  val out = new PrintWriter("advanced-stats.txt")

  private val USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0"
  private val TIMEOUT = 10000
  private val MAX_SIZE_DOC = 12000000
  private val NUMBER_OF_PAGES = 2

  //"238193250" - out of scope, strange bug with matches
  // TODO add possibility to get name by matchURI, to prevent renaming issues
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

  val tiTeams = Set(
    "1838315", //Team Secret
    "39", //EG
    "2586976", //OG
    "2163", // Liquid
    "350190", // Fnatic
    "1375614", // Newbee
    "15", // LGD
    "46", // Empire
    "2512249", // DC
    "1883502", //VP
    "5", //IG
    "3331948", //LFY
    "3214108", // NP
    "4593831", //Planet Dog
    "2640025", // IG.V
    "2108395", //TNC
    "2672298", //Infamous
    "2581813" //Execration
  )

  val teams = Set(
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
    "2621843", //Team Spirit
    "2006913", //Vega
    "1883502", //VP
    "4", //EHOME
    "726228", //VG
    "5", //IG
    "2635099", //CDEC.Y
    "1520578", //CDEC
    "2642171", //Ad Finem
    "59", // Kaipi
    "2783913", // NoDiggity
    "2538753", // Fantastic Five
    "2640099", //Shazam
    "2677025", //FDL
    "2790766", //Rebels
    "2552670", //Prodota
    "55", //PowerRangers
    "2526472", //$5JuNGz$
    "2085365", //-Ninjas-in-Pyjamas-
    "2428289", //Elite Wolves
    "2759317", //Spider pigzs
    "2537636", //Elements Pro Gaming
    "2643401", //CDEC.A
    "1951061" //Newbee.Y
  )

  val popularTeamsLastMonth = getTeamsIdsFrom("https://www.dotabuff.com/esports/teams")

  //

  val allTeams = teams.++(tiTeams).++(popularTeamsLastMonth)

  //

  val teamNames = allTeams.map(getUserName("http://www.dotabuff.com/esports/teams", _))

  // will be commented for a while
  val userNames = List("123")
  //  val userNames = users.map(getUserName)

  val wins = new MultiHashSet[String]
  val losses = new MultiHashSet[String]
  val cmPicks = new MultiHashSet[String]
  val cmBans = new MultiHashSet[String]
  val otherModePicks = new MultiHashSet[String]

  def main(args: Array[String]) {
    println(scala.util.parsing.json.JSONArray(allTeams.toList))
    val allMatches = allTeams.toList.map(getAllMatchesForTeam)
    val combinedMatches = new mutable.HashSet[String]
    for (i <- 0 until allMatches.size) {
      combinedMatches ++= allMatches(i)
    }
    println(combinedMatches.size)
    val playerMatchesData = combinedMatches.map(matchId => getPlayerObjectFromMatch(matchId))
    val players1 = getPlayerObjectFromMatch("https://www.dotabuff.com/matches/3282475291")
    val players2 = getPlayerObjectFromMatch("https://www.dotabuff.com/matches/3280169747")
    val players3 = getPlayerObjectFromMatch("https://www.dotabuff.com/matches/3282263076")
    val players4 = getPlayerObjectFromMatch("https://www.dotabuff.com/matches/3282010340")
    val groupedUpPlayers = (players1.toSeq ++ players2.toSeq ++ players3.toSeq ++ players4.toSeq).groupBy(_._1).mapValues(_.map(_._2).toList)
    var averagedPlayers = groupedUpPlayers.map(x => averageDotaPlayer(x._2))
    println()


    //set the start elo rating
    //    val p = combinedMatches.toList
    //    teams.foreach(teamID => eloRatings.put(teamID, 1500f))
    //    //    val p = scala.util.parsing.json.JSON.parseFull(StdIn.readLine()).get.asInstanceOf[List[String]]
    //    val data = p.sorted.map(game => {
    //      println(s"process match $game")
    //      getMatchObject(game)
    //    })
    //    out.println(scala.util.parsing.json.JSONArray(data))
    //    out.flush()
    //    p.sorted.foreach(game => {
    //
    //      getMatchData(game)
    //      println(s"process match $game")
    //    })
    //    for (i <- 0 until teams.length) {
    //      val matchURI = teams(i)
    //      val name = teamNames(i)
    //      println(s"$name ${eloRatings.getOrElse(matchURI, 1500)}")
    //    }
    //    println()
  }

  def averageDotaPlayer(list: List[DotaPlayer]): DotaPlayer = {
    val builder = new DotaPlayerBuilder()
    val id = list.head.id
    val name = list.head.name
    val avgKills = Math.round(list.map(p => p.kills).sum.toDouble / list.size).toInt
    val avgDeaths = Math.round(list.map(p => p.deaths).sum.toDouble / list.size).toInt
    val avgAssists = Math.round(list.map(p => p.assists).sum.toDouble / list.size).toInt
    val avgLH = Math.round(list.map(p => p.lastHits).sum.toDouble / list.size).toInt
    val avgDenies = Math.round(list.map(p => p.denies).sum.toDouble / list.size).toInt
    val avgGPM = Math.round(list.map(p => p.gpm).sum.toDouble / list.size).toInt
    val avgTowerKills = Math.round(list.map(p => p.towerKill).sum.toDouble / list.size).toInt
    val avgTeamFightPercentage = list.map(p => p.teamFightPercentage).sum / list.size
    val avgObs = Math.round(list.map(p => p.observerWardsPlaced).sum / list.size)
    val avgRunes = Math.round(list.map(p => p.runesPickedUp).sum / list.size)
    val avgFB = Math.round(list.map(p => p.firstBloods).sum / list.size)
    builder.withID(id)
      .withName(name)
      .withKills(avgKills)
      .withDeaths(avgDeaths)
      .withAssists(avgAssists)
      .withLastHits(avgLH)
      .withDenies(avgDenies)
      .withGpm(avgGPM)
      .withTowerKill(avgTowerKills)
      .withTeamFightPercentage(avgTeamFightPercentage)
      .withObservers(avgObs)
      .withRunes(avgRunes)
      .withFB(avgFB)
    builder.build
  }

  def getTeamsIdsFrom(uri: String): Set[String] = {
    try {
      val doc = Jsoup.connect(s"$uri/")
        .userAgent(USER_AGENT)
        .timeout(0)
        .maxBodySize(MAX_SIZE_DOC)
        .get()
      Thread.sleep(TIMEOUT)
      val names = doc.getElementsByAttributeValue("id", " teams-all").get(0).children().get(0).children().get(1).children().toArray().toList.map(x =>
        x.asInstanceOf[Element].getElementsByAttribute("href").attr("href")).map(s => s.substring(s.indexOf("teams") + "teams".length + 1))
      names.toSet
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)
        println(s"$uri")
      }
        Set()
    }
  }

  implicit def string2Int(s: String): Int = if (s.contains("-")) 0 else java.lang.Integer.parseInt(s)

  implicit def string2Float(s: String): Float = java.lang.Float.parseFloat(s)

  def createBasePlayer(element: Element, wardsData: Array[String], runeData: Array[String], fbHeroName: String, towerKillsHeroName: Array[String], teamKills: Int): DotaPlayer = {
    val heroName = element.child(3).children().size() match {
      case 4 => element.child(3).child(3).child(0).child(1).child(0).child(0).text()
      case _ => element.child(3).child(2).child(0).child(1).child(0).child(0).text()
    }
    val builder = new DotaPlayerBuilder()
    val kills = element.child(5).text()
    val assists = element.child(7).text()
    builder
      .withKills(kills)
      .withDeaths(element.child(6).text())
      .withAssists(assists)
      .withLastHits(element.child(9).text())
      .withDenies(element.child(11).text())
      .withGpm(element.child(12).text())
      .withName(element.child(3).child(0).text())
      .withID(element.child(3).child(0).attr("data-tooltip-url"))
    if (element.child(3).child(0).text().isEmpty) {
      builder
        .withName(element.child(3).child(1).text())
        .withID(element.child(3).child(1).attr("data-tooltip-url"))
    }
    val filteredWards = wardsData.filter(_.contains(heroName)).length
    val runes = runeData.filter(_.contains(heroName)).length
    val filteredTowerKills = towerKillsHeroName.filter(_.contains(heroName)).length
    val haveFB = if (fbHeroName.equalsIgnoreCase(heroName)) 1 else 0
    builder
      .withObservers(filteredWards)
      .withTowerKill(filteredTowerKills)
      .withRunes(runes)
      .withFB(haveFB)
      .withTeamFightPercentage((string2Int(kills) + string2Int(assists)).toDouble / teamKills.toDouble)
    builder.build
  }

  def getPlayerObjectFromMatch(matchURI: String): mutable.HashMap[String, DotaPlayer] = {
    try {
      val baseData = Jsoup.connect(s"$matchURI")
        .userAgent(USER_AGENT)
        .timeout(0)
        .maxBodySize(MAX_SIZE_DOC)
        .get()
      Thread.sleep(TIMEOUT)
      val radiantKills = baseData.getElementsByAttributeValue("class", "the-radiant score").text()
      val direKills = baseData.getElementsByAttributeValue("class", "the-dire score").text()
      val players = baseData.getElementsByAttributeValue("data-group-name", "team-table")
        .toArray()
        .map(x => x.asInstanceOf[Element]
          .child(1)
          .children()
          .toArray()
          .toList
          .map(x => x.asInstanceOf[Element]))
        .flatten
      val logDoc = Jsoup.connect(s"$matchURI/log")
        .userAgent(USER_AGENT)
        .timeout(0)
        .maxBodySize(MAX_SIZE_DOC)
        .get()
      Thread.sleep(TIMEOUT)
      val elements = logDoc.getElementsByClass("match-log").get(0).children().toArray()
        .map(x => x.asInstanceOf[Element])
      val wardsData = elements
        .filter(x => x.text().contains("Observer Ward")).filter(x => x.text.contains("placed"))
        .map(x => x.child(0).child(2).child(0).text())
      val runeData = elements
        .filter(x => x.text().contains("Rune")).filter(x => x.text.contains("activated"))
        .map(x => x.child(0).child(2).child(0).text())
      val fbHeroName = elements
        .filter(x => x.text().contains("killed by")).head.child(0).child(2).child(1).text
      val towerKillsHeroName = elements
        .filter(x => x.text.contains("destroys") && x.text.contains("Tower"))
        .map(x => x.child(0).child(2).child(0).text()).filterNot(x => x.contains("Creep"))
      val radiantPlayers = players.take(5).map(x => createBasePlayer(x, wardsData, runeData, fbHeroName, towerKillsHeroName, radiantKills))
      val direPlayers = players.drop(5).map(x => createBasePlayer(x, wardsData, runeData, fbHeroName, towerKillsHeroName, direKills))
      val map = new mutable.HashMap[String, DotaPlayer]()
      radiantPlayers.foreach(p => map.put(p.id, p))
      direPlayers.foreach(p => map.put(p.id, p))
      map
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)
        println(s"$matchURI")
        new mutable.HashMap[String, DotaPlayer]()
      }
    }
  }


  def getUserName(id: String): String = {
    getUserName("http://www.dotabuff.com/players", id)
  }

  def getUserName(baseEndpont: String, id: String): String = {
    try {
      val doc = Jsoup.connect(s"$baseEndpont/$id/")
        .userAgent(USER_AGENT)
        .timeout(0)
        .maxBodySize(MAX_SIZE_DOC)
        .get()
      Thread.sleep(TIMEOUT)
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
    var page = 0
    var added = 1
    while (page < NUMBER_OF_PAGES && added > 0) {
      //    while (added > 0) {
      try {
        val doc = Jsoup.connect(s"$baseEndpoint/$id/matches?page=$page")
          .userAgent(USER_AGENT)
          .timeout(0)
          .maxBodySize(MAX_SIZE_DOC)
          .get()
        Thread.sleep(TIMEOUT)
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

  val eloRatings = new mutable.HashMap[String, Double]()


  def readAllData() = {
    //    val input = scala.util.parsing.json.JSON.parseFull().get.asInstanceOf[List[String]]
  }

  val K: Double = 20d

  def updateEloRating(radiantTeamID: String, direTeamID: String, radiantKills: Int, direKills: Int, win: String) = {
    val oldRadiantElo = eloRatings.getOrElse(radiantTeamID, 0d)
    val oldDireElo = eloRatings.getOrElse(direTeamID, 0d)
    val we = calcWE(oldRadiantElo - oldDireElo)
    var m = 0d
    if (win == "radiant") m = margin(radiantKills - direKills, oldRadiantElo, oldDireElo) else m = margin(radiantKills - direKills, oldDireElo, oldRadiantElo)

    val newRadiantElo = updateRating(oldRadiantElo, m * K, if (win == "radiant") 1 else 0, we)
    val newDireElo = updateRating(oldDireElo, m * K, if (win == "dire") 1 else 0, we)
    eloRatings.put(radiantTeamID, newRadiantElo)
    eloRatings.put(direTeamID, newDireElo)
  }

  def updateRating(oldRating: Double, K: Double, w: Double, we: Double) = oldRating + K * (w - we)

  def calcWE(diffRating: Double) = 1f / (Math.pow(10, (-diffRating / 800)) + 1)

  def probabilityOfWin(ratingA: Double, ratingB: Double) = calcWE(ratingA - ratingB)

  def margin(pointDiff: Int, elow: Double, elol: Double): Double = Math.log(Math.abs(pointDiff) + 1) * (2.2f / ((elow - elol) * 0.0001f + 2.2f))

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

  def getMatchObject(id: String): mutable.HashMap[String, String] = {
    try {
      val doc = Jsoup.connect(s"http://www.dotabuff.com$id/")
        .userAgent(USER_AGENT)
        .timeout(0)
        .maxBodySize(MAX_SIZE_DOC)
        .get()
      Thread.sleep(TIMEOUT)
      var win = ""
      val dire: Elements = doc.select("div[class*=match-result team dire")
      val radiant: Elements = doc.select("div[class*=match-result team radiant")
      if (winOrLoss(dire.text().trim, "Dire Victory")) {
        win = "dire"
      } else {
        win = "radiant"
      }
      val teams = doc.select("header[style*=vertical-align: middle")
      val radiantTeamID = teams.first.children().first().attr("href").split("/")(3)
      val direTeamID = teams.last.children().first().attr("href").split("/")(3)
      val stats = doc.select("td[class*=r-tab r-group-1 cell-centered")
      val radiantKills = stats.get(26).text()
      val radiantDeaths = stats.get(27).text()
      val radiantAssists = stats.get(28).text()
      val radiantKDA: Float = stats.get(29).text()
      val direKills = stats.get(56).text()
      val direDeaths = stats.get(57).text()
      val direAssists = stats.get(57).text()
      val direKDA: Float = stats.get(59).text()
      val map = new mutable.HashMap[String, String]()
      map.put("matchURI", id)
      map.put("win", win)
      map.put("radiant", radiantTeamID)
      map.put("dire", direTeamID)
      map.put("radiantKills", radiantKills.toString)
      map.put("radiantDeaths", radiantDeaths.toString)
      map.put("radiantAssists", radiantAssists.toString)
      map.put("radiantKDA", radiantKDA.toString)
      map.put("direKills", direKills.toString)
      map.put("direDeaths", direDeaths.toString)
      map.put("direAssists", direAssists.toString)
      map.put("direKDA", direKDA.toString)
      map
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)
        println(s"http://www.dotabuff.com$id/")
        new mutable.HashMap[String, String]()
      }
    }
  }

  def getMatchData(id: String) = {
    try {
      val doc = Jsoup.connect(s"http://www.dotabuff.com$id/")
        .userAgent(USER_AGENT)
        .timeout(0)
        .maxBodySize(MAX_SIZE_DOC)
        .get()
      Thread.sleep(TIMEOUT)
      var win = ""
      val dire: Elements = doc.select("div[class*=match-result team dire")
      val radiant: Elements = doc.select("div[class*=match-result team radiant")
      if (winOrLoss(dire.text().trim, "Dire Victory")) {
        win = "dire"
      } else {
        win = "radiant"
      }
      val teams = doc.select("header[style*=vertical-align: middle")
      val radiantTeamID = teams.first.children().first().attr("href").split("/")(3)
      val direTeamID = teams.last.children().first().attr("href").split("/")(3)
      val stats = doc.select("td[class*=r-tab r-group-1 cell-centered")
      val radiantKills = stats.get(26).text()
      val radiantDeaths = stats.get(27).text()
      val radiantAssists = stats.get(28).text()
      val radiantKDA: Float = stats.get(29).text()
      val direKills = stats.get(56).text()
      val direDeaths = stats.get(57).text()
      val direAssists = stats.get(57).text()
      val direKDA: Float = stats.get(59).text()
      updateEloRating(radiantTeamID, direTeamID, radiantKills, direKills, win)
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)
        println(s"http://www.dotabuff.com$id/")
      }
    }
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
}
