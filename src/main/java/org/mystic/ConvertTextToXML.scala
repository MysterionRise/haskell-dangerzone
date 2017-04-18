package org.mystic

import java.io.{File, PrintWriter}
import java.util.Scanner

import org.apache.solr.common.SolrInputDocument


object ConvertTextToXML {

  import org.apache.solr.client.solrj.SolrClient
  import org.apache.solr.client.solrj.impl.HttpSolrClient

  val urlString = "http://192.168.0.88:8983/solr/wiki"
  val solr: SolrClient = new HttpSolrClient(urlString)

  def createXMLFile(title: String, text: StringBuilder) = {
//    val out = new PrintWriter(s"D:\\data\\${title.
//      replaceAllLiterally(" ", "_").
//      replaceAllLiterally("/", "_").
//      replaceAllLiterally("?", "_").
//      replaceAllLiterally("'", "_").
//      replaceAllLiterally("*", "_").
//      replaceAllLiterally("\"", "_").
//      replaceAllLiterally(".", "_").
//      replaceAllLiterally(",", "_").substring(0, 31)}.xml")
    val doc = new SolrInputDocument()
    doc.addField("title", title)
    doc.addField("text", text.toString())
    solr.add(doc)
    //solr.commit()
//    out.print("<add><doc>")
//    out.print(s"""<field name="title">$title</field>""")
//    out.print(s"""<field name="text">${text.toString().trim}</field>""")
//    out.print("</doc></add>")
//    out.flush
//    out.close()
  }

  //000009
  def convertToNDigits(i: Int, n: Int): String = {
    val len = String.valueOf(i).length
    val number = new StringBuilder
    for (_ <- 0 until n - len) {
      number.append("0")
    }
    number.append(String.valueOf(i))
    number.toString()
  }

  def main(args: Array[String]) {
    solr.deleteByQuery("*:*")
    solr.commit()
    for (i <- 0 until 2000) {
      val in = new Scanner(new File(s"D:\\wiki\\20140615-wiki-en_${convertToNDigits(i, 6)}.txt"))
      val text = new StringBuilder
      var title = ""
      while (in.hasNextLine) {
        val line = in.nextLine()
        if (line.startsWith("[[")) {
          if (title.length > 3) {
            createXMLFile(title, text)
            text.clear()
          }
          title = line.replaceAllLiterally("[", "").replaceAllLiterally("]", "")
        } else if (line.startsWith("==")) {
          // nothing
        } else {
          text.append(line)
        }
      }
      solr.commit()
    }
    solr.close()
  }
}
