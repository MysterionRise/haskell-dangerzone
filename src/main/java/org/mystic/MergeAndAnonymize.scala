package org.mystic

import java.io.{File, PrintWriter}
import java.util.Scanner


object MergeAndAnonymize {

  def main(args: Array[String]) {
    val out = new PrintWriter("catalog.json")
    out.println("[")
    //4585206
    for (i <- 0 until 4585206 by 1000) {
      println(i)
      val in = new Scanner(new File(s"catalog-$i"))
      val sb = new StringBuilder
      while (in.hasNextLine) {
        sb.append(in.nextLine())
      }
      val processed = sb.replaceAllLiterally("SolrDocument", "").substring(sb.indexOf("docs=") + 6).replaceAllLiterally("'", "")
        .replaceAllLiterally("\"", "").
        replaceAllLiterally("&quot;", "").replaceAllLiterally("&nbsp;", "").replaceAllLiterally("Kohls", "Hlos").replaceAllLiterally("","")
      val split = processed.replaceAllLiterally("]", "").replaceAllLiterally("[", "").split(",")
      var j = 0
      while (j < split.length) {
        val str = split(j)
        if (str.contains("=")) {
          val arr = str.split("=")
          if (arr(1).contains("<p>")) {
            val tmp = new StringBuilder(arr(1))
            while (!tmp.toString().contains("</p>") && j + 1 < split.length) {
              j += 1
              tmp.append(split(j))
            }
            out.println(s"""${arr(0)}:\"${tmp.toString()}\",""")
          } else if (arr(1).endsWith("}")) {
            out.println(s"""${arr(0)}:\"${arr(1).substring(0, arr(1).length - 1)}\"},""")
          } else {
            out.println(s"""${arr(0)}:\"${arr(1)}\",""")
          }
        }
        j += 1
      }
      out.flush()
      in.close()
    }
    out.println("]")
    out.close()
  }
}
