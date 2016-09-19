package org.mystic

import java.io.{FileOutputStream, PrintWriter}
import java.net.URL

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object VotingDownload {

  val out = new PrintWriter("res")
  val set = new scala.collection.mutable.HashSet[String]()

  def getChildLinks(url: String, prevName: String): Unit = {
    try {
      val doc = Jsoup.connect(url)
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .timeout(0)
        .get()
      out.println(url)
      set.add(url)
      out.flush()
      val script = doc.getElementsByTag("script").get(0).data()
      val sub = script.substring(script.indexOf("window.location.assign") + "window.location.assign".length + 1).replaceAllLiterally("\"", "").replaceAllLiterally("+", "")
      val upd = sub.substring(0, sub.indexOf(")")).replaceAllLiterally("\n", "").replaceAllLiterally("\r", "").replaceAllLiterally("\t", "")
      val name = doc.getElementsByAttributeValue("width", "45%").first().parent().children().text()
      saveExcelFile(upd, name)
      val array: Array[Element] = doc.getElementsByTag("a").toArray(new Array[Element](1))
      val links = array.filter(p => p.attr("style").equalsIgnoreCase("TEXT-DECORATION: none"))
      for (i <- 0 until links.length) {
        val el = links(i)
        val href = el.attr("href")
        if (!href.startsWith("/") && !set.contains(href)) {
          getChildLinks(href, name)
        }
      }
      if (links.isEmpty) {
        val x = array.filter(p => {
          p.text().equalsIgnoreCase("сайт избирательной комиссии субъекта Российской Федерации")
        })
        if (!x.isEmpty) {
          getChildLinks(x(0).attr("href"), name)
        }
      }
    } catch {
      case e: Exception => {
        out.flush()
        println(e)
      }
    }
  }

  def saveExcelFile(u: String, name: String) = {
    val url = new URL(u)
    val httpConn = url.openConnection()
    val inputStream = httpConn.getInputStream()
    val out = new FileOutputStream(s"${name}.xls")
    val buffer = new Array[Byte](4096)
    var bytesRead = inputStream.read(buffer)
    while (bytesRead != -1) {
      out.write(buffer, 0, bytesRead)
      bytesRead = inputStream.read(buffer)
    }
    out.close()
    inputStream.close()
  }

  def main(args: Array[String]) {
    val root1 = "http://www.vybory.izbirkom.ru/region/region/izbirkom?action=show&tvd=100100067795854&vrn=100100067795849&region=0&global=1&sub_region=0&prver=0&pronetvd=0&vibid=100100067795856&type=233"
    val root0 = "http://www.vybory.izbirkom.ru/region/region/izbirkom?action=show&root=1&tvd=100100067795854&vrn=100100067795849&region=0&global=1&sub_region=0&prver=0&pronetvd=0&vibid=100100067795854&type=233"
    val root2 = "http://www.vybory.izbirkom.ru/region/region/izbirkom?action=show&tvd=100100067795856&vrn=100100067795849&region=0&global=1&sub_region=0&prver=0&pronetvd=0&vibid=2012000295675&type=233"
    getChildLinks(root0, "")
  }


}
