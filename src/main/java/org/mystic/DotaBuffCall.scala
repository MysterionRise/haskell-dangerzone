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

  val teamNames = teams.map(getUserName("http://www.dotabuff.com/esports/teams", _))

  // will be commented for a while
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
    var page = 0
    var added = 1
    while (page < 10 && added > 0) {
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

  val eloRatings = new mutable.HashMap[String, Double]()

  /**
    * ["\/matches\/2112506909", "\/matches\/2231932597", "\/matches\/2275976973", "\/matches\/2157050994", "\/matches\/2267054840", "\/matches\/2281540651", "\/matches\/2264012391", "\/matches\/2409398145", "\/matches\/2312210235", "\/matches\/2411397526", "\/matches\/2113367447", "\/matches\/2249183244", "\/matches\/2399012335", "\/matches\/2345234203", "\/matches\/2171050071", "\/matches\/2388461355", "\/matches\/2339022583", "\/matches\/2274494681", "\/matches\/2130634184", "\/matches\/2381980118", "\/matches\/2390589361", "\/matches\/2411940567", "\/matches\/2349984838", "\/matches\/2192542709", "\/matches\/2253206948", "\/matches\/2108173763", "\/matches\/2381310996", "\/matches\/2033255399", "\/matches\/2121160716", "\/matches\/2104713467", "\/matches\/2386426665", "\/matches\/1876710983", "\/matches\/2274095412", "\/matches\/1901711932", "\/matches\/2171471155", "\/matches\/1931539645", "\/matches\/2343759316", "\/matches\/2295756953", "\/matches\/2326609006", "\/matches\/1894030092", "\/matches\/1697818230", "\/matches\/1816157943", "\/matches\/2244422668", "\/matches\/1955500899", "\/matches\/2391064333", "\/matches\/2391598299", "\/matches\/1995989266", "\/matches\/2387912246", "\/matches\/2259970400", "\/matches\/2184910779", "\/matches\/1943612348", "\/matches\/1974889056", "\/matches\/2290769819", "\/matches\/1914177497", "\/matches\/2280083894", "\/matches\/1915570256", "\/matches\/2238773307", "\/matches\/2373452678", "\/matches\/2008680222", "\/matches\/1991666142", "\/matches\/2133116534", "\/matches\/2276386099", "\/matches\/2349048177", "\/matches\/1895378966", "\/matches\/2077552827", "\/matches\/2094688214", "\/matches\/2299765384", "\/matches\/2386888125", "\/matches\/2327527625", "\/matches\/1918457362", "\/matches\/1995917878", "\/matches\/1892515602", "\/matches\/2328130298", "\/matches\/2294816926", "\/matches\/2366901299", "\/matches\/2271815947", "\/matches\/2191531548", "\/matches\/2255589769", "\/matches\/2259519256", "\/matches\/2021037281", "\/matches\/2265246396", "\/matches\/1901927995", "\/matches\/2313867585", "\/matches\/2181038082", "\/matches\/2364934706", "\/matches\/2290033245", "\/matches\/2106735947", "\/matches\/2212885129", "\/matches\/1986797570", "\/matches\/2219984622", "\/matches\/2310970892", "\/matches\/2353050943", "\/matches\/2393660228", "\/matches\/2379473270", "\/matches\/1907429060", "\/matches\/1983978130", "\/matches\/1934734093", "\/matches\/2292934732", "\/matches\/2361906123", "\/matches\/2107957556", "\/matches\/2184452141", "\/matches\/2294893149", "\/matches\/2376341852", "\/matches\/2091713322", "\/matches\/2353461778", "\/matches\/2364898513", "\/matches\/2379750858", "\/matches\/2061212672", "\/matches\/2193441529", "\/matches\/2112056821", "\/matches\/1901799577", "\/matches\/2241908556", "\/matches\/2238910582", "\/matches\/2076256631", "\/matches\/2408596812", "\/matches\/2345075578", "\/matches\/1995624333", "\/matches\/2063916884", "\/matches\/2253211683", "\/matches\/2094893308", "\/matches\/2098788500", "\/matches\/2234586313", "\/matches\/1972999288", "\/matches\/2007285621", "\/matches\/2381777544", "\/matches\/2212688617", "\/matches\/2234990617", "\/matches\/2336707902", "\/matches\/2294902048", "\/matches\/2155091523", "\/matches\/1863232939", "\/matches\/1935692127", "\/matches\/2113655905", "\/matches\/2311461283", "\/matches\/2365829219", "\/matches\/1950217743", "\/matches\/2418415538", "\/matches\/2378469719", "\/matches\/2315870952", "\/matches\/2311832293", "\/matches\/2339478892", "\/matches\/2419012929", "\/matches\/2027353779", "\/matches\/2392509531", "\/matches\/2391153369", "\/matches\/2407750564", "\/matches\/2392380675", "\/matches\/2079482219", "\/matches\/2390803817", "\/matches\/2067225691", "\/matches\/1935355746", "\/matches\/2359431599", "\/matches\/2390185283", "\/matches\/2063199880", "\/matches\/2411032753", "\/matches\/2390888872", "\/matches\/2341083296", "\/matches\/2108256895", "\/matches\/2130312547", "\/matches\/2266387398", "\/matches\/2387774524", "\/matches\/2368152588", "\/matches\/2359544019", "\/matches\/2196139871", "\/matches\/2342752926", "\/matches\/1940406408", "\/matches\/1991112163", "\/matches\/2138638290", "\/matches\/2280655665", "\/matches\/2418664928", "\/matches\/1998318531", "\/matches\/2390297273", "\/matches\/2235065475", "\/matches\/1976726768", "\/matches\/2161051749", "\/matches\/2096395904", "\/matches\/1972685076", "\/matches\/2403511838", "\/matches\/2329077949", "\/matches\/1860038556", "\/matches\/1931705818", "\/matches\/2110583807", "\/matches\/2391528477", "\/matches\/1973117951", "\/matches\/2060552296", "\/matches\/2404059469", "\/matches\/2293735220", "\/matches\/1861768756", "\/matches\/2379112344", "\/matches\/2193255935", "\/matches\/2008858712", "\/matches\/2349030955", "\/matches\/2262015175", "\/matches\/2411196189", "\/matches\/2282395950", "\/matches\/2339885382", "\/matches\/2296964125", "\/matches\/2299658278", "\/matches\/2060683250", "\/matches\/2108218114", "\/matches\/2271813303", "\/matches\/2353055474", "\/matches\/1934624087", "\/matches\/2298421256", "\/matches\/2285475690", "\/matches\/2295667570", "\/matches\/2336212830", "\/matches\/2090853858", "\/matches\/2008682205", "\/matches\/2126181113", "\/matches\/2286336847", "\/matches\/2311393361", "\/matches\/2182314158", "\/matches\/2298565632", "\/matches\/2144799871", "\/matches\/2060759290", "\/matches\/2275109912", "\/matches\/2096247205", "\/matches\/2336805126", "\/matches\/2393265435", "\/matches\/2384394034", "\/matches\/2327813366", "\/matches\/2247571556", "\/matches\/2093575297", "\/matches\/2245391136", "\/matches\/2359871261", "\/matches\/2314895768", "\/matches\/2182047120", "\/matches\/2368044817", "\/matches\/1977381184", "\/matches\/2244209178", "\/matches\/2324180808", "\/matches\/2386821416", "\/matches\/2118895226", "\/matches\/2355147439", "\/matches\/2353764925", "\/matches\/2000926804", "\/matches\/2076536729", "\/matches\/2385687531", "\/matches\/1863295567", "\/matches\/2395723302", "\/matches\/1987847612", "\/matches\/1984422652", "\/matches\/2336872921", "\/matches\/2061254166", "\/matches\/2410888512", "\/matches\/2351405346", "\/matches\/2311164342", "\/matches\/2374230684", "\/matches\/2133394903", "\/matches\/2138297645", "\/matches\/2353290933", "\/matches\/2324295522", "\/matches\/2272612321", "\/matches\/2389863725", "\/matches\/2094863228", "\/matches\/1987953814", "\/matches\/2344839178", "\/matches\/2337898152", "\/matches\/1935285857", "\/matches\/2411460113", "\/matches\/1963528888", "\/matches\/2269147031", "\/matches\/2076295390", "\/matches\/2331093224", "\/matches\/1934607551", "\/matches\/2177005584", "\/matches\/2108681521", "\/matches\/2408659554", "\/matches\/2149223229", "\/matches\/2062698655", "\/matches\/2391638514", "\/matches\/2049611198", "\/matches\/2312495995", "\/matches\/2065960306", "\/matches\/2333488726", "\/matches\/2387375650", "\/matches\/2412139952", "\/matches\/2138129097", "\/matches\/1935428959", "\/matches\/1976981059", "\/matches\/2313728865", "\/matches\/2366779820", "\/matches\/1935124356", "\/matches\/2307359670", "\/matches\/2407996315", "\/matches\/2119048949", "\/matches\/2408139337", "\/matches\/2266506773", "\/matches\/2290842158", "\/matches\/2085059868", "\/matches\/2282547031", "\/matches\/2390853514", "\/matches\/2304912027", "\/matches\/2276191055", "\/matches\/2096571723", "\/matches\/2233273381", "\/matches\/2311374164", "\/matches\/2338435683", "\/matches\/2076416428", "\/matches\/2142501236", "\/matches\/2060491026", "\/matches\/1892921371", "\/matches\/2383834015", "\/matches\/2409573458", "\/matches\/2080442164", "\/matches\/2008124823", "\/matches\/2339205484", "\/matches\/1976550135", "\/matches\/2118785446", "\/matches\/2159163566", "\/matches\/2145325945", "\/matches\/2275625227", "\/matches\/2329009921", "\/matches\/2383860720", "\/matches\/2350829159", "\/matches\/2063082521", "\/matches\/2370501749", "\/matches\/2041025397", "\/matches\/2012851176", "\/matches\/2013578837", "\/matches\/1993659090", "\/matches\/2159333520", "\/matches\/2337562631", "\/matches\/2414962996", "\/matches\/2348598918", "\/matches\/2010788382", "\/matches\/2383344570", "\/matches\/2180068537", "\/matches\/2304759088", "\/matches\/2384503392", "\/matches\/2391372521", "\/matches\/2369259374", "\/matches\/1941583291", "\/matches\/2033137997", "\/matches\/2269762448", "\/matches\/2314200790", "\/matches\/2340129012", "\/matches\/2259753958", "\/matches\/2353646896", "\/matches\/1947775308", "\/matches\/2093833326", "\/matches\/2060491313", "\/matches\/2408934303", "\/matches\/2415097881", "\/matches\/2403278696", "\/matches\/2063729877", "\/matches\/2114390635", "\/matches\/1865268744", "\/matches\/2298985641", "\/matches\/2394287803", "\/matches\/2418580528", "\/matches\/2338332241", "\/matches\/2109840528", "\/matches\/2388609134", "\/matches\/2001089587", "\/matches\/2411150532", "\/matches\/2349268933", "\/matches\/2063091352", "\/matches\/2010381372", "\/matches\/2412676290", "\/matches\/2108085999", "\/matches\/2075614230", "\/matches\/1947612675", "\/matches\/1949504719", "\/matches\/2237221445", "\/matches\/2348709243", "\/matches\/2286205853", "\/matches\/2244419416", "\/matches\/2075800809", "\/matches\/1876955365", "\/matches\/2198359197", "\/matches\/2391225965", "\/matches\/2142818356", "\/matches\/2266917608", "\/matches\/1893119991", "\/matches\/2289911702", "\/matches\/2070303765", "\/matches\/2331234770", "\/matches\/2125638814", "\/matches\/1935806999", "\/matches\/2250490520", "\/matches\/2101299053", "\/matches\/2250335492", "\/matches\/2328919899", "\/matches\/2277086897", "\/matches\/2343186672", "\/matches\/2108320103", "\/matches\/2171587249", "\/matches\/2276143929", "\/matches\/2032614329", "\/matches\/1932637212", "\/matches\/2176498930", "\/matches\/2398678361", "\/matches\/2008512273", "\/matches\/2029612992", "\/matches\/2367522521", "\/matches\/2135865842", "\/matches\/1998652203", "\/matches\/2101792278", "\/matches\/2009554343", "\/matches\/2171246036", "\/matches\/2261801641", "\/matches\/2125994001", "\/matches\/2243618430", "\/matches\/2370453638", "\/matches\/2408658682", "\/matches\/2111878655", "\/matches\/2063371941", "\/matches\/2265410457", "\/matches\/2107876927", "\/matches\/2065046175", "\/matches\/2337167320", "\/matches\/2137885726", "\/matches\/2115832770", "\/matches\/2343557932", "\/matches\/2220343765", "\/matches\/2385353363", "\/matches\/2269344768", "\/matches\/2004778900", "\/matches\/2370561778", "\/matches\/2192320439", "\/matches\/2415350631", "\/matches\/2349189263", "\/matches\/2077571730", "\/matches\/2069209160", "\/matches\/1673630085", "\/matches\/2260697384", "\/matches\/2181195992", "\/matches\/2336128689", "\/matches\/2065482587", "\/matches\/2063816118", "\/matches\/2266631748", "\/matches\/2280168354", "\/matches\/2237387866", "\/matches\/2388880554", "\/matches\/2302196958", "\/matches\/2009460415", "\/matches\/2340758007", "\/matches\/2149379026", "\/matches\/2362809743", "\/matches\/2142612562", "\/matches\/2080584907", "\/matches\/2381472192", "\/matches\/2094759749", "\/matches\/2049756270", "\/matches\/2156956446", "\/matches\/1984130258", "\/matches\/2159399305", "\/matches\/2166362075", "\/matches\/2227501950", "\/matches\/1993868977", "\/matches\/2063467937", "\/matches\/2033684435", "\/matches\/2384713764", "\/matches\/1933095987", "\/matches\/2306254123", "\/matches\/2062788365", "\/matches\/2350733980", "\/matches\/2244497975", "\/matches\/2316737771", "\/matches\/1998547629", "\/matches\/2387167278", "\/matches\/2147302916", "\/matches\/2365455182", "\/matches\/2362110197", "\/matches\/2077303843", "\/matches\/2099425092", "\/matches\/2107746062", "\/matches\/2101449868", "\/matches\/2386118329", "\/matches\/2262395818", "\/matches\/2223209824", "\/matches\/2340498996", "\/matches\/1965895338", "\/matches\/2133491135", "\/matches\/2288550050", "\/matches\/2340596245", "\/matches\/2277063721", "\/matches\/2196501846", "\/matches\/2254080063", "\/matches\/2353463767", "\/matches\/2061999071", "\/matches\/2348869325", "\/matches\/1876896881", "\/matches\/2365934194", "\/matches\/2381152347", "\/matches\/2381442759", "\/matches\/2290543588", "\/matches\/2305984834", "\/matches\/2171209198", "\/matches\/2339763609", "\/matches\/2393750290", "\/matches\/2328668279", "\/matches\/2076499367", "\/matches\/2311076788", "\/matches\/2092412217", "\/matches\/2111906731", "\/matches\/2107644923", "\/matches\/2152521794", "\/matches\/2270057131", "\/matches\/2337306581", "\/matches\/2106851099", "\/matches\/2077734638", "\/matches\/1935113449", "\/matches\/2322253788", "\/matches\/1935535689", "\/matches\/2308283589", "\/matches\/2294742322", "\/matches\/1907275614", "\/matches\/2258536592", "\/matches\/2294319841", "\/matches\/2249111437", "\/matches\/2383927996", "\/matches\/1982075607", "\/matches\/2409157187", "\/matches\/2179855075", "\/matches\/2179624943", "\/matches\/2264101159", "\/matches\/1995781321", "\/matches\/2353901277", "\/matches\/2244258226", "\/matches\/2008514673", "\/matches\/2246762975", "\/matches\/2057847701", "\/matches\/2412105328", "\/matches\/2142589648", "\/matches\/2302207368", "\/matches\/2000762142", "\/matches\/2386930631", "\/matches\/2394234710", "\/matches\/2305621043", "\/matches\/2384765855", "\/matches\/2394117404", "\/matches\/2063614085", "\/matches\/1842833975", "\/matches\/2410889717", "\/matches\/2316471723", "\/matches\/2118921504", "\/matches\/2252802310", "\/matches\/2387450856", "\/matches\/1981322667", "\/matches\/2294001498", "\/matches\/2252931465", "\/matches\/2261797172", "\/matches\/2261625736", "\/matches\/2234307020", "\/matches\/2289792314", "\/matches\/1975030290", "\/matches\/2316657060", "\/matches\/1899850056", "\/matches\/2294472980", "\/matches\/2147824328", "\/matches\/2253350261", "\/matches\/2351975832", "\/matches\/2387141641", "\/matches\/2109664389", "\/matches\/2345194817", "\/matches\/2306177749", "\/matches\/2367339596", "\/matches\/2040545523", "\/matches\/2306091117", "\/matches\/2185060254", "\/matches\/2246848426", "\/matches\/2262864352", "\/matches\/2337848719", "\/matches\/2112609850", "\/matches\/2232081920", "\/matches\/2371485530", "\/matches\/2092543480", "\/matches\/2297061518", "\/matches\/2238593073", "\/matches\/2008190776", "\/matches\/2109708585", "\/matches\/2108582802", "\/matches\/2027852285", "\/matches\/2391053148", "\/matches\/2263423814", "\/matches\/2281212975", "\/matches\/1993071556", "\/matches\/2350949696", "\/matches\/2183857503", "\/matches\/2388967647", "\/matches\/2353132853", "\/matches\/2273592062", "\/matches\/2178782204", "\/matches\/2326451319", "\/matches\/2147432544", "\/matches\/2383460994", "\/matches\/2333710502", "\/matches\/2366742497", "\/matches\/2061014289", "\/matches\/2378720566", "\/matches\/2390299864", "\/matches\/2258834387", "\/matches\/2339325664", "\/matches\/2339501046", "\/matches\/2384874638", "\/matches\/2313611856", "\/matches\/2339945518", "\/matches\/2398914028", "\/matches\/2276722356", "\/matches\/1974298339", "\/matches\/2128594760", "\/matches\/2301270577", "\/matches\/2298212432", "\/matches\/2409258294", "\/matches\/2066888438", "\/matches\/2117886431", "\/matches\/1941979787", "\/matches\/2305916358", "\/matches\/2193321592", "\/matches\/2312390844", "\/matches\/2252923362", "\/matches\/2140228532", "\/matches\/2393302052", "\/matches\/2326537816", "\/matches\/2364896489", "\/matches\/2345271263", "\/matches\/2159281122", "\/matches\/2339302270", "\/matches\/2271901701", "\/matches\/2276865122", "\/matches\/1947178316", "\/matches\/2316261510", "\/matches\/2133332942", "\/matches\/2337441499", "\/matches\/1911763339", "\/matches\/2077718141", "\/matches\/1992867471", "\/matches\/2233998262", "\/matches\/2298891159", "\/matches\/1974697195", "\/matches\/2294388767", "\/matches\/2311316934", "\/matches\/2345010284", "\/matches\/2294251743", "\/matches\/2107024470", "\/matches\/2070058798", "\/matches\/2001143037", "\/matches\/2337627115", "\/matches\/1973200167", "\/matches\/2308367637", "\/matches\/1963377105", "\/matches\/2353049287", "\/matches\/2281165446", "\/matches\/2000678327", "\/matches\/2295859695", "\/matches\/2062636723", "\/matches\/2116001889", "\/matches\/2076348113", "\/matches\/1945878388", "\/matches\/1977437645", "\/matches\/2340708351", "\/matches\/2343459314", "\/matches\/2346784326", "\/matches\/2415461299", "\/matches\/1998424806", "\/matches\/2238503292", "\/matches\/2341524831", "\/matches\/2095305164", "\/matches\/2349903933", "\/matches\/2109842031", "\/matches\/2154825443", "\/matches\/2244081320", "\/matches\/2273917666", "\/matches\/2137992345", "\/matches\/2344805144", "\/matches\/2409698555", "\/matches\/2307049415", "\/matches\/1917589873", "\/matches\/2355233139", "\/matches\/2379622728", "\/matches\/2397931660", "\/matches\/2381831351", "\/matches\/2077430052", "\/matches\/1984698282", "\/matches\/1893213280", "\/matches\/2395420018", "\/matches\/2389050459", "\/matches\/2395592820", "\/matches\/2395932638", "\/matches\/2126115511", "\/matches\/2273583335", "\/matches\/2339712125", "\/matches\/2393101969", "\/matches\/2081957054", "\/matches\/2263476430", "\/matches\/2354950006", "\/matches\/2325793557", "\/matches\/2236061800", "\/matches\/2393938301", "\/matches\/2238663103", "\/matches\/2365122870", "\/matches\/2403608367", "\/matches\/2110347651", "\/matches\/2112310424", "\/matches\/2177257309", "\/matches\/2082106725", "\/matches\/2387043302", "\/matches\/2326325947", "\/matches\/1892602691", "\/matches\/2263331572", "\/matches\/1950565767", "\/matches\/2260927146", "\/matches\/2379747403", "\/matches\/2061913138", "\/matches\/2306102101", "\/matches\/1893683763", "\/matches\/2236266806", "\/matches\/2336959660", "\/matches\/1943053613", "\/matches\/2192088670", "\/matches\/2159077225", "\/matches\/2271430080", "\/matches\/2062642139", "\/matches\/2353346356", "\/matches\/2140564047", "\/matches\/2398011497", "\/matches\/1929522822", "\/matches\/2358275776", "\/matches\/2093135715", "\/matches\/1893865729", "\/matches\/2258049161", "\/matches\/2313244332", "\/matches\/1991317227", "\/matches\/2233452056", "\/matches\/2303767365", "\/matches\/2157061866", "\/matches\/2235117172", "\/matches\/1975123151", "\/matches\/2302333305", "\/matches\/2350832545", "\/matches\/1944132605", "\/matches\/2008002986", "\/matches\/1918729624", "\/matches\/2135628263", "\/matches\/2076679006", "\/matches\/2163091710", "\/matches\/2286600495", "\/matches\/2304810288", "\/matches\/1914713381", "\/matches\/2107923519", "\/matches\/2068205321", "\/matches\/2296602401", "\/matches\/1911352385", "\/matches\/2126100455", "\/matches\/2281450916", "\/matches\/2310182649", "\/matches\/2178853523", "\/matches\/2225030620", "\/matches\/2337723585", "\/matches\/2308026992", "\/matches\/2266746787", "\/matches\/2298713609", "\/matches\/2115403451", "\/matches\/2017374358", "\/matches\/2146691699", "\/matches\/1949609142", "\/matches\/2004863930", "\/matches\/2176488482", "\/matches\/2109652802", "\/matches\/1697618202", "\/matches\/2283662925", "\/matches\/1947084585", "\/matches\/2269916277", "\/matches\/2362462047", "\/matches\/2028559247", "\/matches\/2344723962", "\/matches\/2296159612", "\/matches\/2332796666", "\/matches\/1815910733", "\/matches\/2386717919", "\/matches\/2108392454", "\/matches\/2067086468", "\/matches\/1897532328", "\/matches\/2198763106", "\/matches\/2125977137", "\/matches\/2393469420", "\/matches\/2408217778", "\/matches\/1694607827", "\/matches\/2128557124", "\/matches\/2411603661", "\/matches\/1945112557", "\/matches\/2365338673", "\/matches\/2304260015", "\/matches\/1993802548", "\/matches\/2243077822", "\/matches\/2342078911", "\/matches\/2150046912", "\/matches\/2075462434", "\/matches\/2309613092", "\/matches\/2066679779", "\/matches\/2244121749", "\/matches\/2312613889", "\/matches\/2276305563", "\/matches\/2296020893", "\/matches\/2212798006", "\/matches\/2152595788", "\/matches\/1993753508", "\/matches\/1995503349", "\/matches\/1977296293", "\/matches\/2032776461", "\/matches\/2236803896", "\/matches\/1976132158", "\/matches\/2379842403", "\/matches\/2069609831", "\/matches\/2052521446", "\/matches\/2274673314", "\/matches\/2109084419", "\/matches\/1944054411", "\/matches\/2261910568", "\/matches\/2356272796", "\/matches\/2330179733", "\/matches\/2389294947", "\/matches\/2409298043", "\/matches\/2257292101", "\/matches\/2319810734", "\/matches\/2336781247", "\/matches\/2196306427", "\/matches\/1863677215", "\/matches\/2030735369", "\/matches\/1895314854", "\/matches\/2040909613", "\/matches\/2405526656", "\/matches\/1915477141", "\/matches\/2386562091", "\/matches\/2234722442", "\/matches\/2332626658", "\/matches\/1882341972", "\/matches\/1949769021", "\/matches\/2388037202", "\/matches\/2155027337", "\/matches\/2391528862", "\/matches\/1832765587", "\/matches\/2068092670", "\/matches\/1911348429", "\/matches\/2365497749", "\/matches\/2329779065", "\/matches\/2115207703", "\/matches\/2132910825", "\/matches\/2128345682", "\/matches\/2262268980", "\/matches\/2272581715", "\/matches\/1893000472", "\/matches\/1993447295", "\/matches\/2181696818", "\/matches\/2311283171", "\/matches\/2195561641", "\/matches\/2303937815", "\/matches\/1995727685", "\/matches\/2325479729", "\/matches\/2408631374", "\/matches\/2337474794", "\/matches\/2313878925", "\/matches\/1991188850", "\/matches\/2357638993", "\/matches\/2283535730", "\/matches\/2351288729", "\/matches\/2258765860", "\/matches\/2388295812", "\/matches\/2265139416", "\/matches\/2302371978", "\/matches\/2307871746", "\/matches\/1974091429", "\/matches\/1977059438", "\/matches\/1914619443", "\/matches\/2345153510", "\/matches\/2271900300", "\/matches\/2184275180", "\/matches\/2108052578", "\/matches\/2077929558", "\/matches\/2266268427", "\/matches\/1973112556", "\/matches\/2075980748", "\/matches\/2010914863", "\/matches\/1873868674", "\/matches\/1694506494", "\/matches\/2337677959", "\/matches\/2350095534", "\/matches\/2275715560", "\/matches\/1835070722", "\/matches\/2160938760", "\/matches\/2259402537", "\/matches\/2391685864", "\/matches\/2273992647", "\/matches\/2367248719", "\/matches\/2392635929", "\/matches\/2247868538", "\/matches\/2064423945", "\/matches\/2261667351", "\/matches\/2389073738", "\/matches\/2239001296", "\/matches\/2360967787", "\/matches\/1943424630", "\/matches\/2263278120", "\/matches\/2093093353", "\/matches\/2316339839", "\/matches\/2335845402", "\/matches\/2138255770", "\/matches\/2263375981", "\/matches\/1963151513", "\/matches\/2108580048", "\/matches\/2183947071", "\/matches\/1950077300", "\/matches\/2362153623", "\/matches\/2091999033", "\/matches\/2101585345", "\/matches\/2340253964", "\/matches\/2247437812", "\/matches\/2314704802", "\/matches\/2302290458", "\/matches\/2383681500", "\/matches\/1897673409", "\/matches\/2367281043", "\/matches\/2265530348", "\/matches\/2342839981", "\/matches\/2147344757", "\/matches\/2384256117", "\/matches\/2387598938", "\/matches\/2323891271", "\/matches\/1918614355", "\/matches\/2123593362", "\/matches\/2271707036", "\/matches\/2092696829", "\/matches\/2288571374", "\/matches\/2337193144", "\/matches\/2348831585", "\/matches\/2109974422", "\/matches\/2130306179", "\/matches\/2310941113", "\/matches\/2302121046", "\/matches\/2272085250", "\/matches\/1918959324", "\/matches\/2379924234", "\/matches\/2096068677", "\/matches\/2108797408", "\/matches\/2296612160", "\/matches\/2296704308", "\/matches\/2013039115", "\/matches\/1977511522", "\/matches\/2381274264", "\/matches\/2178692811", "\/matches\/1943130017", "\/matches\/2114301791", "\/matches\/2366565955", "\/matches\/2128381185", "\/matches\/2126051389", "\/matches\/2159253863", "\/matches\/2065765933", "\/matches\/2358060936", "\/matches\/2392084446", "\/matches\/2393003920", "\/matches\/2316878245", "\/matches\/2260829788", "\/matches\/1941444525", "\/matches\/2384912976", "\/matches\/2118625650", "\/matches\/2178739661", "\/matches\/2191055532", "\/matches\/2292712754", "\/matches\/2391460754", "\/matches\/2311553749", "\/matches\/1909892980", "\/matches\/2016383165", "\/matches\/2344521896", "\/matches\/2358375544", "\/matches\/2057800235", "\/matches\/2357832186", "\/matches\/2104788081", "\/matches\/2062557027", "\/matches\/2294796276", "\/matches\/2314634747", "\/matches\/1934603943", "\/matches\/2061039829", "\/matches\/2285199737", "\/matches\/2313352169", "\/matches\/1863558051", "\/matches\/2355556952", "\/matches\/2310293354", "\/matches\/2130463088", "\/matches\/2277025656", "\/matches\/2367179963", "\/matches\/2359311428", "\/matches\/2000987764", "\/matches\/2353167913", "\/matches\/2145372581", "\/matches\/2183812156", "\/matches\/2246014052", "\/matches\/2309808585", "\/matches\/2311680604", "\/matches\/1939511048", "\/matches\/2016100756", "\/matches\/2344913504", "\/matches\/2198604205", "\/matches\/2304828380", "\/matches\/1986700969", "\/matches\/2355362615", "\/matches\/2127885491", "\/matches\/1835109527", "\/matches\/2061413869", "\/matches\/2373940139", "\/matches\/2010559717", "\/matches\/2356168901", "\/matches\/2114632374", "\/matches\/2319713551", "\/matches\/2313452953", "\/matches\/2257233280", "\/matches\/2409012917", "\/matches\/2391139376", "\/matches\/2142732998", "\/matches\/2387892271", "\/matches\/2095238951", "\/matches\/1974521164", "\/matches\/1919071372", "\/matches\/2109725036", "\/matches\/2008391627", "\/matches\/2264224302", "\/matches\/1885790437", "\/matches\/2094733443", "\/matches\/2274902953", "\/matches\/2296427900", "\/matches\/1861662552", "\/matches\/2063000977", "\/matches\/2027754211", "\/matches\/2299839539", "\/matches\/2114744832", "\/matches\/1977354706", "\/matches\/1993348638", "\/matches\/2290129859", "\/matches\/2261549873", "\/matches\/2266730605", "\/matches\/2251282233", "\/matches\/2195950707", "\/matches\/1955590443", "\/matches\/2316821675", "\/matches\/2330868333", "\/matches\/2171376942", "\/matches\/2118226357", "\/matches\/2261862548", "\/matches\/2351533108", "\/matches\/2154907851", "\/matches\/2396853029", "\/matches\/1984529130", "\/matches\/2292850179", "\/matches\/1977204801", "\/matches\/1972514674", "\/matches\/2265385458", "\/matches\/1976836813", "\/matches\/1864520760", "\/matches\/2278985683", "\/matches\/2275850886", "\/matches\/2351557179", "\/matches\/2341013826", "\/matches\/2195748703", "\/matches\/2360795429", "\/matches\/2411081738", "\/matches\/2113520583", "\/matches\/2333353481", "\/matches\/2077320904", "\/matches\/2245111686", "\/matches\/2264143587", "\/matches\/2111841347", "\/matches\/2361741329", "\/matches\/2117348373", "\/matches\/2180278248", "\/matches\/1947992644", "\/matches\/2033006219", "\/matches\/2108326859", "\/matches\/2411569279", "\/matches\/2309440910", "\/matches\/2259461835", "\/matches\/2261550195", "\/matches\/1834919789", "\/matches\/2176618946", "\/matches\/1940220599", "\/matches\/1975207688", "\/matches\/1906861533", "\/matches\/1918839537", "\/matches\/2272189132", "\/matches\/1948102678", "\/matches\/2008015176", "\/matches\/2283780151", "\/matches\/2354005458", "\/matches\/2107740985", "\/matches\/1885889110", "\/matches\/2261826737", "\/matches\/1911583755", "\/matches\/2339804749", "\/matches\/1941150735", "\/matches\/2351868364", "\/matches\/2184665045", "\/matches\/2033930307", "\/matches\/2389174213", "\/matches\/2104666935", "\/matches\/1895212479", "\/matches\/2128285457", "\/matches\/2339288350", "\/matches\/2277126457", "\/matches\/2138497064", "\/matches\/2278290649", "\/matches\/2282497032", "\/matches\/2325627305", "\/matches\/2111816681", "\/matches\/2276307873", "\/matches\/2064343834", "\/matches\/1697676707", "\/matches\/2159440958", "\/matches\/2411342644", "\/matches\/2075759693", "\/matches\/2156836226", "\/matches\/1864620776", "\/matches\/2339374695", "\/matches\/2013646526", "\/matches\/2387680209", "\/matches\/2294704677", "\/matches\/2390575728", "\/matches\/1986923590", "\/matches\/2176744667", "\/matches\/2314841134", "\/matches\/1981406723", "\/matches\/2130449105", "\/matches\/2241199446", "\/matches\/2061229184", "\/matches\/2339573779", "\/matches\/2076097364", "\/matches\/2411686594", "\/matches\/2410954215", "\/matches\/2411780572", "\/matches\/2362849384", "\/matches\/2383979325", "\/matches\/2343695628", "\/matches\/2253970195", "\/matches\/1983817900", "\/matches\/1876805812", "\/matches\/2177549858", "\/matches\/1954487890", "\/matches\/1943762819", "\/matches\/2329921521", "\/matches\/1945236890", "\/matches\/2395282129", "\/matches\/2079593863", "\/matches\/2076028877", "\/matches\/1974218607", "\/matches\/1941706137", "\/matches\/2415540518", "\/matches\/2366558590", "\/matches\/2365584366", "\/matches\/2083137467", "\/matches\/2350740718", "\/matches\/2395144767", "\/matches\/1981265554", "\/matches\/2313777896", "\/matches\/2199144312", "\/matches\/2136072447", "\/matches\/2299142936", "\/matches\/2007363351", "\/matches\/2197006785", "\/matches\/2410892086", "\/matches\/2115065683", "\/matches\/2198252097", "\/matches\/2114485241", "\/matches\/1943922409", "\/matches\/2261825015", "\/matches\/2341777762", "\/matches\/2340417387", "\/matches\/1993252604", "\/matches\/2389225228", "\/matches\/2119021432", "\/matches\/2176882495", "\/matches\/2340020714", "\/matches\/2375937888", "\/matches\/2337596375", "\/matches\/1934749751", "\/matches\/1832673928", "\/matches\/2378929428", "\/matches\/2060301497", "\/matches\/2391732708", "\/matches\/1983228466", "\/matches\/2356060013", "\/matches\/2264320082", "\/matches\/2326203661", "\/matches\/2016250491", "\/matches\/2112812155", "\/matches\/2353143542", "\/matches\/2098572895", "\/matches\/2176559949", "\/matches\/1876850379", "\/matches\/2280266168", "\/matches\/2277103235", "\/matches\/2344015548", "\/matches\/2108168598", "\/matches\/2408612651", "\/matches\/2109702182", "\/matches\/2393384802", "\/matches\/2385563388", "\/matches\/2110775831", "\/matches\/2303862171", "\/matches\/2107839445", "\/matches\/2155057012", "\/matches\/2262146627", "\/matches\/2294692603", "\/matches\/2288411870", "\/matches\/1950431595", "\/matches\/2110484192", "\/matches\/2277009400", "\/matches\/2383979763", "\/matches\/2286463569", "\/matches\/2107662815", "\/matches\/2365013206", "\/matches\/2330334586", "\/matches\/2308104740", "\/matches\/2158987415", "\/matches\/2405630794", "\/matches\/2296754322", "\/matches\/2299273472", "\/matches\/1998201818", "\/matches\/2325206983", "\/matches\/2330191470", "\/matches\/1993608233", "\/matches\/2328421602", "\/matches\/2090238182", "\/matches\/1668437721", "\/matches\/2301627120", "\/matches\/1991371320", "\/matches\/1998715126", "\/matches\/2181130001", "\/matches\/1998496853", "\/matches\/2390400509", "\/matches\/2408676984", "\/matches\/2063102147", "\/matches\/2052610024", "\/matches\/2345007751", "\/matches\/2391023817", "\/matches\/1929624798", "\/matches\/2198057551", "\/matches\/2135974027", "\/matches\/2107988999", "\/matches\/2077933059", "\/matches\/1945422325", "\/matches\/2381687924", "\/matches\/2410895838", "\/matches\/2328799155", "\/matches\/2409156577", "\/matches\/2366674896", "\/matches\/2390097852", "\/matches\/2412797277", "\/matches\/1977091866", "\/matches\/2411277988", "\/matches\/2398546934", "\/matches\/1995395126", "\/matches\/2070184901", "\/matches\/2280975813", "\/matches\/2370497200", "\/matches\/2156677318", "\/matches\/2367029703", "\/matches\/2388164307", "\/matches\/2384114355", "\/matches\/2020957492", "\/matches\/1932995967", "\/matches\/2384119069", "\/matches\/2032505340", "\/matches\/2242985193", "\/matches\/2001032297", "\/matches\/2352859075", "\/matches\/2337082016", "\/matches\/2412232933", "\/matches\/2343245310", "\/matches\/2240896236", "\/matches\/2193524509", "\/matches\/2385800287", "\/matches\/2195854935", "\/matches\/2110015348", "\/matches\/1955653337", "\/matches\/2317015332", "\/matches\/2245231965", "\/matches\/2365714841", "\/matches\/2335979382", "\/matches\/2307111596", "\/matches\/2069063624", "\/matches\/2142507011", "\/matches\/2355766229", "\/matches\/2340355178", "\/matches\/1668506691", "\/matches\/2351701644", "\/matches\/2244366843", "\/matches\/2063517372", "\/matches\/2411796041", "\/matches\/1983152233", "\/matches\/2193199056", "\/matches\/2276559230", "\/matches\/2065112786", "\/matches\/2031108240", "\/matches\/2196830083", "\/matches\/2150227455", "\/matches\/2201456363", "\/matches\/2061873247", "\/matches\/2351713821", "\/matches\/2075276122", "\/matches\/2330655963", "\/matches\/2068342969", "\/matches\/2412553018", "\/matches\/2301794639", "\/matches\/2022206966", "\/matches\/2063609588", "\/matches\/2344177693", "\/matches\/2070247171", "\/matches\/1914031829", "\/matches\/2240978055", "\/matches\/2179423318", "\/matches\/2258638667", "\/matches\/2015564443", "\/matches\/2022253592", "\/matches\/2341630715", "\/matches\/1981953141", "\/matches\/2176558984", "\/matches\/2138574904", "\/matches\/1934837069", "\/matches\/1991532094", "\/matches\/2137873648", "\/matches\/2261055900", "\/matches\/2351567336", "\/matches\/2181823003", "\/matches\/2234857740", "\/matches\/2179252492", "\/matches\/2109724816", "\/matches\/1939902040", "\/matches\/2299087475", "\/matches\/2386994671", "\/matches\/2408914595", "\/matches\/2343796570", "\/matches\/2117469845", "\/matches\/2149706429", "\/matches\/2370551453", "\/matches\/2278826586", "\/matches\/2183792909", "\/matches\/2029938095", "\/matches\/2366671842", "\/matches\/2081841527", "\/matches\/2360919761", "\/matches\/2378192463", "\/matches\/2310960129", "\/matches\/2339598654", "\/matches\/2069416649", "\/matches\/2248038208", "\/matches\/2342341468", "\/matches\/1697737102", "\/matches\/2030827740", "\/matches\/2282286087", "\/matches\/2184118645", "\/matches\/2308134121", "\/matches\/2342232730", "\/matches\/1959124140", "\/matches\/2028386347", "\/matches\/2201993554", "\/matches\/1892430943", "\/matches\/2092415945", "\/matches\/2290349869", "\/matches\/2391226429", "\/matches\/1954560296", "\/matches\/2304058412", "\/matches\/1934912028", "\/matches\/2195617106", "\/matches\/2158700627", "\/matches\/1945972951", "\/matches\/1895466802", "\/matches\/1893506793", "\/matches\/2325977586", "\/matches\/1911214336", "\/matches\/2355174754", "\/matches\/2220092232", "\/matches\/2259459605", "\/matches\/2339708397", "\/matches\/1865343286", "\/matches\/2250475410", "\/matches\/2063221959", "\/matches\/2236100944", "\/matches\/2388266565", "\/matches\/2365608522", "\/matches\/2299511198", "\/matches\/2308052705", "\/matches\/2017283870", "\/matches\/1882446478", "\/matches\/2063470904", "\/matches\/2344332453", "\/matches\/1955721321", "\/matches\/2360101443", "\/matches\/2065589675", "\/matches\/2365487702", "\/matches\/2008880159", "\/matches\/2078130243", "\/matches\/2008367535", "\/matches\/1983981524", "\/matches\/2343659407", "\/matches\/2183961604", "\/matches\/2390681523", "\/matches\/2409393490", "\/matches\/2307521825", "\/matches\/2180141650", "\/matches\/1876548273", "\/matches\/2245748404", "\/matches\/2311039360", "\/matches\/2107660182", "\/matches\/1948190353", "\/matches\/2306022280", "\/matches\/1991602467", "\/matches\/1932432373", "\/matches\/2110262306", "\/matches\/2281366296", "\/matches\/2408704984", "\/matches\/2408813096", "\/matches\/2311171068", "\/matches\/2293597182", "\/matches\/2408596949", "\/matches\/2272855055", "\/matches\/2107778368", "\/matches\/2365310129", "\/matches\/2016482876", "\/matches\/2389940352", "\/matches\/2364761262", "\/matches\/2062014366", "\/matches\/2241349099", "\/matches\/2198141418", "\/matches\/1979107752", "\/matches\/2392853767", "\/matches\/2395715351", "\/matches\/2128246705", "\/matches\/2117383841", "\/matches\/2282710823", "\/matches\/2374089029", "\/matches\/2198989614", "\/matches\/2259387342", "\/matches\/1932536887", "\/matches\/2392766501", "\/matches\/2238522557", "\/matches\/2338335953", "\/matches\/2240805127", "\/matches\/2299914029", "\/matches\/2412622303", "\/matches\/2378982742", "\/matches\/2157033796", "\/matches\/1979222731", "\/matches\/2327418889", "\/matches\/2108139739", "\/matches\/2194067318", "\/matches\/1981562036", "\/matches\/2268946578", "\/matches\/2337733265", "\/matches\/2091791379", "\/matches\/1993737370", "\/matches\/2150143180", "\/matches\/2352856357", "\/matches\/2066144864", "\/matches\/2331370689", "\/matches\/2305462403", "\/matches\/2315970571", "\/matches\/2250660872", "\/matches\/2191243186", "\/matches\/1943279606", "\/matches\/2352927036", "\/matches\/1939712379", "\/matches\/2093670236", "\/matches\/2092620719", "\/matches\/1911457811", "\/matches\/2243172383", "\/matches\/2338058263", "\/matches\/2340232452", "\/matches\/2391068098", "\/matches\/2365060082", "\/matches\/2404141174", "\/matches\/2232009478", "\/matches\/2351377434", "\/matches\/2157002584", "\/matches\/2302025599", "\/matches\/2384991738", "\/matches\/2177741136", "\/matches\/2242041197", "\/matches\/2179222246", "\/matches\/2408739164", "\/matches\/2201019839", "\/matches\/2379852373", "\/matches\/2324037868", "\/matches\/2271509027", "\/matches\/2351243324", "\/matches\/2307242465", "\/matches\/2070114438", "\/matches\/2379136488", "\/matches\/2123439452", "\/matches\/2060377693", "\/matches\/2259807966", "\/matches\/2277160709", "\/matches\/2154633446", "\/matches\/2109649818", "\/matches\/2147761381", "\/matches\/2015945300", "\/matches\/2107751616", "\/matches\/2248973565", "\/matches\/2175861659", "\/matches\/2394013952", "\/matches\/2274781122", "\/matches\/2142374663", "\/matches\/2183894124", "\/matches\/2082949424", "\/matches\/2234172489", "\/matches\/2098706328", "\/matches\/2314043924", "\/matches\/2236659920", "\/matches\/2260529834", "\/matches\/2107854977", "\/matches\/2362954509", "\/matches\/2076724492", "\/matches\/2112955180", "\/matches\/2403997603", "\/matches\/1876624178", "\/matches\/1911497618", "\/matches\/2278225671", "\/matches\/2360692445", "\/matches\/2273639994", "\/matches\/1843041961", "\/matches\/1899675778", "\/matches\/2076227618", "\/matches\/2065193805", "\/matches\/2314984317", "\/matches\/2029823980", "\/matches\/1907055118", "\/matches\/2075411351", "\/matches\/2095187327", "\/matches\/2147532535", "\/matches\/2412028250", "\/matches\/2140117293", "\/matches\/2394164458", "\/matches\/2418854718", "\/matches\/1976212191", "\/matches\/1977186331", "\/matches\/2193849107", "\/matches\/2013794982", "\/matches\/2114856246", "\/matches\/2394217435", "\/matches\/2108453277", "\/matches\/1885998499", "\/matches\/2257719869", "\/matches\/2113890637", "\/matches\/2352938620", "\/matches\/2152307007", "\/matches\/2013668459", "\/matches\/2277176456", "\/matches\/2118027293", "\/matches\/2099792140", "\/matches\/2067132796", "\/matches\/2093179602", "\/matches\/2408862401", "\/matches\/2180357452", "\/matches\/1945561195", "\/matches\/1816014829", "\/matches\/2322146027", "\/matches\/2410966933", "\/matches\/2296487261", "\/matches\/2261916819", "\/matches\/2349128145", "\/matches\/2368225724", "\/matches\/1984801723", "\/matches\/1965679626", "\/matches\/1822174770", "\/matches\/2194260255", "\/matches\/2243081615", "\/matches\/2140472792", "\/matches\/2393242178", "\/matches\/1998032937", "\/matches\/2396251847", "\/matches\/2040714804", "\/matches\/2349231942", "\/matches\/1947403563", "\/matches\/2112172308", "\/matches\/2182565525", "\/matches\/2252674583", "\/matches\/2410965721", "\/matches\/1975286617", "\/matches\/1941871897", "\/matches\/2064195270", "\/matches\/2227565782", "\/matches\/1993547070", "\/matches\/1941267862", "\/matches\/2288838440", "\/matches\/2177171063", "\/matches\/1981733381", "\/matches\/2033562446", "\/matches\/1822326913", "\/matches\/1909999488", "\/matches\/2410986712", "\/matches\/2078120447", "\/matches\/2142526018", "\/matches\/2330391415", "\/matches\/2253065365", "\/matches\/1873698557", "\/matches\/2293877695", "\/matches\/2077418186", "\/matches\/2337571619", "\/matches\/2297916411", "\/matches\/2263082502", "\/matches\/1945721183", "\/matches\/1907594589", "\/matches\/2301634839", "\/matches\/2201116123", "\/matches\/2147090044", "\/matches\/2333606939", "\/matches\/2310440093", "\/matches\/2360573746", "\/matches\/2236495988", "\/matches\/2012940442", "\/matches\/2351105196", "\/matches\/2156516128", "\/matches\/2193658590", "\/matches\/2201624014", "\/matches\/2259389172", "\/matches\/2390909301", "\/matches\/2339112979", "\/matches\/2257589567", "\/matches\/1995874559", "\/matches\/2197232058", "\/matches\/2301873517", "\/matches\/2128035024", "\/matches\/2396777809", "\/matches\/2030985868", "\/matches\/2273633172", "\/matches\/1863365195", "\/matches\/2191144529", "\/matches\/2301479595", "\/matches\/2178572793", "\/matches\/2338218681", "\/matches\/1972994203", "\/matches\/2109829139", "\/matches\/2069724674", "\/matches\/2379905353", "\/matches\/1945023833", "\/matches\/2373343805", "\/matches\/2257309114", "\/matches\/2301538718", "\/matches\/2147674179", "\/matches\/2080280770", "\/matches\/2304188492", "\/matches\/2310520710", "\/matches\/2158982046", "\/matches\/2062500670", "\/matches\/2178661580", "\/matches\/2154521505", "\/matches\/2049487960", "\/matches\/2355894068", "\/matches\/2096694313", "\/matches\/2028007805", "\/matches\/2275028257", "\/matches\/2285344796", "\/matches\/2064017925", "\/matches\/2099590491", "\/matches\/2314432332", "\/matches\/2384635556", "\/matches\/2147218943", "\/matches\/2178970934", "\/matches\/2388960049", "\/matches\/1959233883", "\/matches\/1917819065", "\/matches\/2255357723", "\/matches\/2379562978", "\/matches\/2362620248", "\/matches\/2108944190", "\/matches\/2272446775", "\/matches\/1816272474", "\/matches\/2288468378", "\/matches\/2010299165", "\/matches\/1917711851", "\/matches\/2378041536", "\/matches\/2251463414", "\/matches\/1859904973", "\/matches\/2327947633", "\/matches\/2355057833", "\/matches\/2257914125", "\/matches\/2288770591", "\/matches\/2109659438", "\/matches\/2316391716", "\/matches\/2237484053", "\/matches\/2266670346", "\/matches\/2396087229", "\/matches\/2095366171", "\/matches\/2238827131", "\/matches\/2316068955", "\/matches\/2257410421", "\/matches\/2027263838", "\/matches\/2261999920", "\/matches\/1673552043", "\/matches\/2257230310", "\/matches\/2281285778", "\/matches\/2201838263", "\/matches\/2004989065", "\/matches\/2379320481", "\/matches\/2362315882", "\/matches\/2135446610", "\/matches\/2108548897", "\/matches\/1991426957", "\/matches\/1900046746", "\/matches\/2408737158", "\/matches\/2191441268", "\/matches\/2362722554", "\/matches\/2339614293", "\/matches\/1934739764", "\/matches\/2115601185", "\/matches\/2075276894", "\/matches\/2130443958", "\/matches\/2381963417", "\/matches\/2386721399", "\/matches\/2376814504", "\/matches\/2076381332", "\/matches\/2266901795", "\/matches\/1834706362", "\/matches\/2107983537", "\/matches\/2388752391", "\/matches\/2015617679", "\/matches\/2360272238", "\/matches\/2015692611", "\/matches\/2311280185", "\/matches\/2379415477", "\/matches\/2418319460", "\/matches\/2341919205", "\/matches\/2343325287", "\/matches\/1972824031", "\/matches\/2395973342", "\/matches\/2322316701", "\/matches\/1931620410", "\/matches\/2260061145", "\/matches\/2362288452", "\/matches\/2269480271", "\/matches\/2332978193", "\/matches\/2166445828", "\/matches\/2159170413", "\/matches\/2403395785", "\/matches\/1882570317", "\/matches\/2255471845", "\/matches\/2272698490", "\/matches\/2298086930", "\/matches\/2239156248", "\/matches\/2272369021", "\/matches\/2237478172", "\/matches\/2339442450", "\/matches\/2029688671", "\/matches\/2280990166"]

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
    //    //set the start elo rating
    //    val p = combinedMatches.toList
    teams.foreach(teamID => eloRatings.put(teamID, 1500f))
    val p = scala.util.parsing.json.JSON.parseFull(StdIn.readLine()).get.asInstanceOf[List[String]]
    p.sorted.foreach(game => {
      getMatchData(game)
    })
    for (i <- 0 until teams.length) {
      val id = teams(i)
      val name = teamNames(i)
      println(s"$name ${eloRatings.getOrElse(id, 1500)}")
    }
    println()
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

  def calcWE(diffRating: Double) = 1f / (Math.pow(10, (-diffRating / 400)) + 1)

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

  def getMatchData(id: String) = {
    try {
      val doc = Jsoup.connect(s"http://www.dotabuff.com$id/")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      Thread.sleep(10000)
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
      val radiantKills = stats.get(26).text().toInt
      val radiantDeaths = stats.get(27).text().toInt
      val radiantAssists = stats.get(28).text().toInt
      val radiantKDA = stats.get(29).text().toFloat
      val direKills = stats.get(56).text().toInt
      val direDeaths = stats.get(57).text().toInt
      val direAssists = stats.get(57).text().toInt
      val direKDA = stats.get(59).text().toFloat
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
