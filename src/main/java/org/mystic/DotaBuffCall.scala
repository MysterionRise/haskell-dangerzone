package org.mystic

import java.util.Scanner

import org.jsoup.Jsoup


object DotaBuffCall {

  def main(args: Array[String]): Unit = {
    val in = new Scanner(System.in)
    val n = in.nextInt()
    in.nextLine()
    for (i <- 0 until n) {
      val name = in.nextLine()
      val doc = Jsoup.connect(name)
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .get()
      println(doc.getAllElements)
    }
  }
}