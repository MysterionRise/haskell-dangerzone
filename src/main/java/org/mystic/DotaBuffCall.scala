package org.mystic

import java.io.PrintWriter
import java.util.Scanner

import org.apache.http.client.methods.HttpGet
import org.apache.http.client.{HttpResponseException, ResponseHandler}
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}

import scala.xml.parsing.XhtmlParser

object DotaBuffCall {

  def main(args: Array[String]): Unit = {
    val in = new Scanner(System.in)
    val n = in.nextInt()

    var client = new DefaultHttpClient
    in.nextLine()
    for (i <- 0 until n) {
      try {
        client = new DefaultHttpClient
        val name = in.nextLine()
        val get: HttpGet = new HttpGet(name)
        val responseHandler: ResponseHandler[String] = new BasicResponseHandler
        val responseBody = client.execute(get, responseHandler)
        if (responseBody.length > 0) {
          val html = XhtmlParser(scala.io.Source.fromString(responseBody))

          val out = new PrintWriter(name.substring(name.lastIndexOf("/") + 1))
          out.println((html \ "html" \ "body" \ "div[1]" \ "div[7]" \ "div[2]" \ "div[1]" \ "div[2]").text)
          out.println((html \ "html" \ "body" \ "div[1]" \ "div[7]" \ "div[3]" \ "div[1]").text)
//          out.println((xml \\ "header-content-secondary").text)
          //          xml \\ "html/body/div[1]/div[7]/div[2]/div[1]/div[2]"

          // header
          ///html/body/div[1]/div[7]/div[2]/div[1]/div[2]

          // body
          ///html/body/div[1]/div[7]/div[3]/div[1]
          //          val start = responseBody.indexOf("<div class=\"match-show\">")
          //          val end = responseBody.indexOf("<div id=\"kunkka-bottom-leaderboard\"")
          //          out.println(responseBody.substring(start, end))
          out.flush()
          out.close()
          //          val patternBody: Pattern = Pattern.compile("\"_score\":([^]])+]")
          //          val m: Matcher = patternBody.matcher(responseBody)
          //          if (m.find) {
          //            val data: Array[String] = m.group(0).split("/")
          //            val score_family: String = data(0).replace("\"all_pubs_datasource\":[\"[[\\\"", "")
          //            val tmp = score_family.split(",")
          //            val score: String = tmp(0).replace("\"_score\":", "")
          //            val pubId: String = tmp(1).replace("\"fields\":{", "")
          //            System.out.println(s"score = ${score}, publicationID = ${pubId} pn=${pn}")
        }
      }
      catch {
        case e: HttpResponseException => {
          val message: String = e.getStatusCode + ", " + e.getMessage
          System.out.println("Error " + message)
        }
      } finally {
        client.getConnectionManager.shutdown
      }
    }
  }
}