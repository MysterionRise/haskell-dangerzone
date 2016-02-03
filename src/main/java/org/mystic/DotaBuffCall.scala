package org.mystic

import java.io.PrintWriter
import java.util.Scanner

import org.apache.http.client.methods.HttpGet
import org.apache.http.client.{HttpResponseException, ResponseHandler}
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}

import scala.xml.XML

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
          val html = XML.loadString(responseBody.substring(responseBody.indexOf("<body>"), responseBody.indexOf("</body>") + 7))

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