package org.mystic

import java.io.File

import com.github.tototoshi.csv.CSVWriter
import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, TextNode}
import org.jsoup.select.Elements

case class Player(name: String, stats: Array[SeasonStat]) {

}

case class SeasonStat(year: String, games: Int, pts: Float, reb: Float, ast: Float, blk: Float, stl: Float) {

}


object BasketRefCall {

  def getLeagueAvg(): List[SeasonStat] = {
    var stats = List[SeasonStat]()
    try {
      val doc = Jsoup.connect("http://www.basketball-reference.com/leagues/NBA_stats.html")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      Thread.sleep(10)
      val seasonStats = doc.getElementById("stats").childNodes().get(6).childNodes()
      for (i <- 0 until seasonStats.size() by 2) {
        val season = seasonStats.get(i)
        val clazz = season.attr("class")
        if (clazz.isEmpty) {
          val s = season.childNodes()
          val year = s.get(1).asInstanceOf[Element].text()
          val gText: String = s.get(6).asInstanceOf[Element].text()
          val games = if (gText.isEmpty) 0 else java.lang.Integer.parseInt(gText)
          val stlText: String = s.get(18).asInstanceOf[Element].text()
          val stl = if (stlText.isEmpty) 0 else java.lang.Float.parseFloat(stlText)
          val blkText: String = s.get(19).asInstanceOf[Element].text()
          val blk = if (blkText.isEmpty) 0 else java.lang.Float.parseFloat(blkText)
          val rebText: String = s.get(16).asInstanceOf[Element].text()
          val reb = if (rebText.isEmpty) 0 else java.lang.Float.parseFloat(rebText)
          val astText: String = s.get(17).asInstanceOf[Element].text()
          val ast = if (astText.isEmpty) 0 else java.lang.Float.parseFloat(astText)
          val ptsText: String = s.get(22).asInstanceOf[Element].text()
          val pts = if (ptsText.isEmpty) 0 else java.lang.Float.parseFloat(ptsText)
          stats = stats.::(SeasonStat(year, games, pts, reb, ast, blk, stl))
        }
      }
      stats
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)
      }
        stats
    }
  }

  def getPlayerStats(baseEndpont: String, id: String): Player = {
    try {
      val doc = Jsoup.connect(s"$baseEndpont/$id/")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      Thread.sleep(10)
      val playerStats = doc.getElementsByAttributeValue("itemtype", "http://schema.org/Person").get(0).childNodes()
      val playerName = playerStats.get(1).asInstanceOf[Element].text()
      val comment = doc.getElementsByAttributeValue("id", "all_totals").get(0).childNodes()
      val d = Jsoup.parse(comment.get(5).attr("comment"))
      val rows = d.getElementsByClass("full_table")
      val perSeasonStats = new Array[SeasonStat](rows.size())
      for (i <- 0 until rows.size()) {
        val row = rows.get(i)
        val stats = row.childNodes()
        val year = stats.get(0).asInstanceOf[Element].text()
        val stlText: String = stats.get(25).asInstanceOf[Element].text()
        val stl = if (stlText.isEmpty) 0 else Integer.parseInt(stlText)
        val blkText: String = stats.get(26).asInstanceOf[Element].text()
        val blk = if (blkText.isEmpty) 0 else Integer.parseInt(blkText)
        val rebText: String = stats.get(23).asInstanceOf[Element].text()
        val reb = if (rebText.isEmpty) 0 else Integer.parseInt(rebText)
        val astText: String = stats.get(24).asInstanceOf[Element].text()
        val ast = if (astText.isEmpty) 0 else Integer.parseInt(astText)
        val ptsText: String = stats.get(29).asInstanceOf[Element].text()
        val pts = if (ptsText.isEmpty) 0 else Integer.parseInt(ptsText)
        perSeasonStats(i) = SeasonStat(year, 1, pts, reb, ast, blk, stl)
      }
      Player(playerName, perSeasonStats)
    } catch {
      case e: Exception => {
        e.printStackTrace(System.out)

      }
        // return userId if some error happens
        Player(null, null)
    }
  }

  def main(args: Array[String]): Unit = {
    //    val baseEndpoint = "http://www.basketball-reference.com"
    //    val players = "<a href='/players/a/'>A</a><div><td><a href=\"/players/a/abdulka01.html\">Kareem Abdul-Jabbar</a>*,&nbsp;<a href=\"/players/a/allenra02.html\">Ray Allen</a>,&nbsp;<a href=\"/players/a/arizipa01.html\">Paul Arizin</a>*,&nbsp;<a href=\"/players/a/anthoca01.html\"><strong>Carmelo Anthony</strong></a>,&nbsp;<a href=\"/players/a/architi01.html\">Tiny Archibald</a>*,&nbsp;<a href=\"/players/a/aldrila01.html\"><strong>LaMarcus Aldridge</strong></a>,&nbsp;&hellip;</div></li><li><a href='/players/b/'>B</a><div><td><a href=\"/players/b/barklch01.html\">Charles Barkley</a>*,&nbsp;<a href=\"/players/b/bryanko01.html\">Kobe Bryant</a>,&nbsp;<a href=\"/players/b/birdla01.html\">Larry Bird</a>*,&nbsp;<a href=\"/players/b/bellawa01.html\">Walt Bellamy</a>*,&nbsp;<a href=\"/players/b/barryri01.html\">Rick Barry</a>*,&nbsp;<a href=\"/players/b/billuch01.html\">Chauncey Billups</a>,&nbsp;&hellip;</div></li><li><a href='/players/c/'>C</a><div><td><a href=\"/players/c/chambwi01.html\">Wilt Chamberlain</a>*,&nbsp;<a href=\"/players/c/cartevi01.html\"><strong>Vince Carter</strong></a>,&nbsp;<a href=\"/players/c/cheekma01.html\">Maurice Cheeks</a>,&nbsp;<a href=\"/players/c/chandty01.html\"><strong>Tyson Chandler</strong></a>,&nbsp;<a href=\"/players/c/cummite01.html\">Terry Cummings</a>,&nbsp;<a href=\"/players/c/cousybo01.html\">Bob Cousy</a>*,&nbsp;&hellip;</div></li><li><a href='/players/d/'>D</a><div><td><a href=\"/players/d/duncati01.html\">Tim Duncan</a>,&nbsp;<a href=\"/players/d/drexlcl01.html\">Clyde Drexler</a>*,&nbsp;<a href=\"/players/d/dantlad01.html\">Adrian Dantley</a>*,&nbsp;<a href=\"/players/d/duranke01.html\"><strong>Kevin Durant</strong></a>,&nbsp;<a href=\"/players/d/divacvl01.html\">Vlade Divac</a>,&nbsp;<a href=\"/players/d/davisda01.html\">Dale Davis</a>,&nbsp;&hellip;</div></li><li><a href='/players/e/'>E</a><div><td><a href=\"/players/e/ervinju01.html\">Julius Erving</a>*,&nbsp;<a href=\"/players/e/ewingpa01.html\">Patrick Ewing</a>*,&nbsp;<a href=\"/players/e/englial01.html\">Alex English</a>*,&nbsp;<a href=\"/players/e/ellisda01.html\">Dale Ellis</a>,&nbsp;<a href=\"/players/e/edwarja01.html\">James Edwards</a>,&nbsp;<a href=\"/players/e/eakinji01.html\">Jim Eakins</a>,&nbsp;&hellip;</div></li><li><a href='/players/f/'>F</a><div><td><a href=\"/players/f/fraziwa01.html\">Walt Frazier</a>*,&nbsp;<a href=\"/players/f/finlemi01.html\">Michael Finley</a>,&nbsp;<a href=\"/players/f/foustla01.html\">Larry Foust</a>,&nbsp;<a href=\"/players/f/freewo01.html\">World B. Free</a>,&nbsp;<a href=\"/players/f/fishede01.html\">Derek Fisher</a>,&nbsp;<a href=\"/players/f/freemdo01.html\">Donnie Freeman</a>,&nbsp;&hellip;</div></li><li><a href='/players/g/'>G</a><div><td><a href=\"/players/g/garneke01.html\"><strong>Kevin Garnett</strong></a>,&nbsp;<a href=\"/players/g/gilmoar01.html\">Artis Gilmore</a>*,&nbsp;<a href=\"/players/g/gasolpa01.html\"><strong>Pau Gasol</strong></a>,&nbsp;<a href=\"/players/g/grantho01.html\">Horace Grant</a>,&nbsp;<a href=\"/players/g/gervige01.html\">George Gervin</a>*,&nbsp;<a href=\"/players/g/greerha01.html\">Hal Greer</a>*,&nbsp;&hellip;</div></li><li><a href='/players/h/'>H</a><div><td><a href=\"/players/h/havlijo01.html\">John Havlicek</a>*,&nbsp;<a href=\"/players/h/hayesel01.html\">Elvin Hayes</a>*,&nbsp;<a href=\"/players/h/howelba01.html\">Bailey Howell</a>*,&nbsp;<a href=\"/players/h/howardw01.html\"><strong>Dwight Howard</strong></a>,&nbsp;<a href=\"/players/h/hornaje01.html\">Jeff Hornacek</a>,&nbsp;<a href=\"/players/h/hillgr01.html\">Grant Hill</a>,&nbsp;&hellip;</div></li><li><a href='/players/i/'>I</a><div><td><a href=\"/players/i/isselda01.html\">Dan Issel</a>*,&nbsp;<a href=\"/players/i/iversal01.html\">Allen Iverson</a>*,&nbsp;<a href=\"/players/i/iguodan01.html\"><strong>Andre Iguodala</strong></a>,&nbsp;<a href=\"/players/i/ilgauzy01.html\">Zydrunas Ilgauskas</a>,&nbsp;<a href=\"/players/i/ibakase01.html\"><strong>Serge Ibaka</strong></a>,&nbsp;<a href=\"/players/i/ilyaser01.html\"><strong>Ersan Ilyasova</strong></a>,&nbsp;&hellip;</div></li><li><a href='/players/j/'>J</a><div><td><a href=\"/players/j/jordami01.html\">Michael Jordan</a>*,&nbsp;<a href=\"/players/j/jamesle01.html\"><strong>LeBron James</strong></a>,&nbsp;<a href=\"/players/j/johnsma02.html\">Magic Johnson</a>*,&nbsp;<a href=\"/players/j/jonesed02.html\">Eddie Jones</a>,&nbsp;<a href=\"/players/j/jonesbo01.html\">Bobby Jones</a>,&nbsp;<a href=\"/players/j/johnske02.html\">Kevin Johnson</a>,&nbsp;&hellip;</div></li><li><a href='/players/k/'>K</a><div><td><a href=\"/players/k/kiddja01.html\">Jason Kidd</a>,&nbsp;<a href=\"/players/k/kempsh01.html\">Shawn Kemp</a>,&nbsp;<a href=\"/players/k/kirilan01.html\">Andrei Kirilenko</a>,&nbsp;<a href=\"/players/k/kingbe01.html\">Bernard King</a>*,&nbsp;<a href=\"/players/k/kerseje01.html\">Jerome Kersey</a>,&nbsp;<a href=\"/players/k/knighbi01.html\">Billy Knight</a>,&nbsp;&hellip;</div></li><li><a href='/players/l/'>L</a><div><td><a href=\"/players/l/laniebo01.html\">Bob Lanier</a>*,&nbsp;<a href=\"/players/l/laimbbi01.html\">Bill Laimbeer</a>,&nbsp;<a href=\"/players/l/lucasje01.html\">Jerry Lucas</a>*,&nbsp;<a href=\"/players/l/lewisra02.html\">Rashard Lewis</a>,&nbsp;<a href=\"/players/l/lovelcl01.html\">Clyde Lovellette</a>*,&nbsp;<a href=\"/players/l/leeda02.html\"><strong>David Lee</strong></a>,&nbsp;&hellip;</div></li><li><a href='/players/m/'>M</a><div><td><a href=\"/players/m/malonka01.html\">Karl Malone</a>*,&nbsp;<a href=\"/players/m/malonmo01.html\">Moses Malone</a>*,&nbsp;<a href=\"/players/m/millere01.html\">Reggie Miller</a>*,&nbsp;<a href=\"/players/m/mariosh01.html\">Shawn Marion</a>,&nbsp;<a href=\"/players/m/mutomdi01.html\">Dikembe Mutombo</a>*,&nbsp;<a href=\"/players/m/mchalke01.html\">Kevin McHale</a>*,&nbsp;&hellip;</div></li><li><a href='/players/n/'>N</a><div><td><a href=\"/players/n/nowitdi01.html\"><strong>Dirk Nowitzki</strong></a>,&nbsp;<a href=\"/players/n/nashst01.html\">Steve Nash</a>,&nbsp;<a href=\"/players/n/nancela01.html\">Larry Nance</a>,&nbsp;<a href=\"/players/n/nelsodo01.html\">Don Nelson</a>*,&nbsp;<a href=\"/players/n/natersw01.html\">Swen Nater</a>,&nbsp;<a href=\"/players/n/noahjo01.html\"><strong>Joakim Noah</strong></a>,&nbsp;&hellip;</div></li><li><a href='/players/o/'>O</a><div><td><a href=\"/players/o/onealsh01.html\">Shaquille O'Neal</a>*,&nbsp;<a href=\"/players/o/olajuha01.html\">Hakeem Olajuwon</a>*,&nbsp;<a href=\"/players/o/oaklech01.html\">Charles Oakley</a>,&nbsp;<a href=\"/players/o/odomla01.html\">Lamar Odom</a>,&nbsp;<a href=\"/players/o/onealje01.html\">Jermaine O'Neal</a>,&nbsp;<a href=\"/players/o/owensto01.html\">Tom Owens</a>,&nbsp;&hellip;</div></li><li><a href='/players/p/'>P</a><div><td><a href=\"/players/p/piercpa01.html\"><strong>Paul Pierce</strong></a>,&nbsp;<a href=\"/players/p/parisro01.html\">Robert Parish</a>*,&nbsp;<a href=\"/players/p/paytoga01.html\">Gary Payton</a>*,&nbsp;<a href=\"/players/p/paulch01.html\"><strong>Chris Paul</strong></a>,&nbsp;<a href=\"/players/p/pettibo01.html\">Bob Pettit</a>*,&nbsp;<a href=\"/players/p/pippesc01.html\">Scottie Pippen</a>*,&nbsp;&hellip;</div></li><li><a href='/players/q/'>Q</a><div><td><a href=\"/players/q/quinnch01.html\">Chris Quinn</a>,&nbsp;<a href=\"/players/q/quickbo01.html\">Bob Quick</a>,&nbsp;<a href=\"/players/q/quinnbr01.html\">Brian Quinnett</a>,&nbsp;&hellip;</div></li><li><a href='/players/r/'>R</a><div><td><a href=\"/players/r/roberos01.html\">Oscar Robertson</a>*,&nbsp;<a href=\"/players/r/robinda01.html\">David Robinson</a>*,&nbsp;<a href=\"/players/r/russebi01.html\">Bill Russell</a>*,&nbsp;<a href=\"/players/r/rodmade01.html\">Dennis Rodman</a>*,&nbsp;<a href=\"/players/r/robincl02.html\">Clifford Robinson</a>,&nbsp;<a href=\"/players/r/ricegl01.html\">Glen Rice</a>,&nbsp;&hellip;</div></li><li><a href='/players/s/'>S</a><div><td><a href=\"/players/s/stockjo01.html\">John Stockton</a>*,&nbsp;<a href=\"/players/s/schaydo01.html\">Dolph Schayes</a>*,&nbsp;<a href=\"/players/s/sikmaja01.html\">Jack Sikma</a>,&nbsp;<a href=\"/players/s/schrede01.html\">Detlef Schrempf</a>,&nbsp;<a href=\"/players/s/stoudam01.html\">Amar'e Stoudemire</a>,&nbsp;<a href=\"/players/s/stricro02.html\">Rod Strickland</a>,&nbsp;&hellip;</div></li><li><a href='/players/t/'>T</a><div><td><a href=\"/players/t/thorpot01.html\">Otis Thorpe</a>,&nbsp;<a href=\"/players/t/terryja01.html\"><strong>Jason Terry</strong></a>,&nbsp;<a href=\"/players/t/thomais01.html\">Isiah Thomas</a>*,&nbsp;<a href=\"/players/t/thurmna01.html\">Nate Thurmond</a>*,&nbsp;<a href=\"/players/t/twymaja01.html\">Jack Twyman</a>*,&nbsp;<a href=\"/players/t/tomjaru01.html\">Rudy Tomjanovich</a>,&nbsp;&hellip;</div></li><li><a href='/players/u/'>U</a><div><td><a href=\"/players/u/unselwe01.html\">Wes Unseld</a>*,&nbsp;<a href=\"/players/u/udrihbe01.html\"><strong>Beno Udrih</strong></a>,&nbsp;<a href=\"/players/u/udokaim01.html\">Ime Udoka</a>,&nbsp;<a href=\"/players/u/udohek01.html\"><strong>Ekpe Udoh</strong></a>,&nbsp;<a href=\"/players/u/upshake01.html\">Kelvin Upshaw</a>,&nbsp;<a href=\"/players/u/uzohbe01.html\">Ben Uzoh</a>,&nbsp;&hellip;</div></li><li><a href='/players/v/'>V</a><div><td><a href=\"/players/v/vandeki01.html\">Kiki Vandeweghe</a>,&nbsp;<a href=\"/players/v/vanardi01.html\">Dick Van Arsdale</a>,&nbsp;<a href=\"/players/v/vanexni01.html\">Nick Van Exel</a>,&nbsp;<a href=\"/players/v/vanlino01.html\">Norm Van Lier</a>,&nbsp;<a href=\"/players/v/varejan01.html\"><strong>Anderson Varejao</strong></a>,&nbsp;<a href=\"/players/v/vanhoke01.html\">Keith Van Horn</a>,&nbsp;&hellip;</div></li><li><a href='/players/w/'>W</a><div><td><a href=\"/players/w/westje01.html\">Jerry West</a>*,&nbsp;<a href=\"/players/w/willibu01.html\">Buck Williams</a>,&nbsp;<a href=\"/players/w/wilkido01.html\">Dominique Wilkins</a>*,&nbsp;<a href=\"/players/w/walkech01.html\">Chet Walker</a>*,&nbsp;<a href=\"/players/w/wadedw01.html\"><strong>Dwyane Wade</strong></a>,&nbsp;<a href=\"/players/w/wallara01.html\">Rasheed Wallace</a>,&nbsp;&hellip;</div></li><li>X</li>\n<li><a href='/players/y/'>Y</a><div><td><a href=\"/players/y/yardlge01.html\">George Yardley</a>*,&nbsp;<a href=\"/players/y/youngth01.html\"><strong>Thaddeus Young</strong></a>,&nbsp;<a href=\"/players/y/youngda01.html\">Danny Young</a>,&nbsp;<a href=\"/players/y/youngni01.html\"><strong>Nick Young</strong></a>,&nbsp;<a href=\"/players/y/youngsa01.html\"><strong>Sam Young</strong></a>,&nbsp;<a href=\"/players/y/youngmi01.html\">Michael Young</a>,&nbsp;&hellip;</div></li><li><a href='/players/z/'>Z</a><div><td><a href=\"/players/z/zasloma01.html\">Max Zaslofsky</a>,&nbsp;<a href=\"/players/z/zellety01.html\"><strong>Tyler Zeller</strong></a>,&nbsp;<a href=\"/players/z/zelleco01.html\"><strong>Cody Zeller</strong></a>,&nbsp;<a href=\"/players/z/zawolze01.html\">Zeke Zawoluk</a>,&nbsp;<a href=\"/players/z/zelleha01.html\">Harry Zeller</a>,&nbsp;<a href=\"/players/z/zhizhwa01.html\">Wang Zhizhi</a>".split("a href=\"").drop(1).map(x => x.substring(0, x.indexOf(">") - 1)).map(uri => getPlayerStats(baseEndpoint, uri))
    //    val f = new File("out.csv")
    //    val writer = CSVWriter.open(f)
    //    writer.writeRow(List("name", "year", "reb", "ast", "stl", "blk", "pts"))
    //    players.foreach(p => {
    //      p.stats.foreach(stat => writer.writeRow(List(p.name, stat.year, stat.reb, stat.ast, stat.stl, stat.blk, stat.pts)))
    //    })
    //    writer.close
    val x = getLeagueAvg()
    val f = new File("avg.csv")
    val writer = CSVWriter.open(f)
    writer.writeRow(List("year", "games", "reb", "ast", "stl", "blk", "pts"))
    x.foreach(p => {
      writer.writeRow(List(p.year, p.games, p.reb, p.ast, p.stl, p.blk, p.pts))
    })
    writer.close
    println()
  }
}
