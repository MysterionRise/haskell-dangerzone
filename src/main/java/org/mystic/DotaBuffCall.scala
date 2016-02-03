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
      // who win the game
      println(doc.select("div[class*=match-result team dire").text())
      println(doc.select("div[class*=match-result team radiant").text())
      // stats for entire game
      println(doc.select("div[class*=header-content-secondary]").text())
      // stats per team
      val teamsResults = doc.select("div[class*=team-results")
      // radiant
      println(teamsResults.first().text)
      // dire
      println(teamsResults.last().text)
      val teamAbilityBuilds = doc.select("div[class*=match-ability-builds")
      // radiant
      println(teamAbilityBuilds.first().text)
      // dire
      println(teamAbilityBuilds.last().text)
    }
  }
}