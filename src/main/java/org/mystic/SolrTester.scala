package org.mystic

import java.io.PrintWriter

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.solr.client.solrj.{SolrClient, SolrQuery}
import org.apache.solr.client.solrj.impl.HttpSolrClient
import scala.util.Random

object SolrTester {

  val TEST_SIZE = 1001

  val WORD_SIZE = 3

  val INDEX_V = 2

  val urlString1 = "http://192.168.0.88:8983/solr/wiki"
  val solr1: SolrClient = new HttpSolrClient(urlString1)

  val urlString2 = "http://192.168.0.88:8984/solr/wiki"
  val solr2: SolrClient = new HttpSolrClient(urlString2)

  val letters = "qwertyuiopasdfghjklzxcvbnm"
  val length = letters.length

  val rand = new Random()

  def generateWord(letters: String, WORD_SIZE: Int): String = {
    val word = new StringBuilder
    for (_ <- 0 until WORD_SIZE) {
      word.append(letters.charAt(rand.nextInt(length - 1)))
    }
    word.toString()
  }


  def main(args: Array[String]) {
    val out = new PrintWriter(s"$INDEX_V-test-$TEST_SIZE-$WORD_SIZE.txt")
    val wildcardStats = new DescriptiveStatistics()
    val saStats = new DescriptiveStatistics()
    for (i <- 0 until TEST_SIZE) {
      if (i % (TEST_SIZE / 10) == 0) {
        println((10 * i) / (TEST_SIZE / 10) + "%")
      }
      val word = generateWord(letters, WORD_SIZE)
      val query = s"*${word}*"
      wildcardStats.addValue(runWildcardQuery(query))
      saStats.addValue(runSuffixArrayQuery(query))
    }
//    for (i <- 'a' to 'z')
//      for (j <- 'a' to 'z')
//        for (k <- 'a' to 'z') {
//          val query = s"*$i$j$k*"
//          val wild = runWildcardQuery(query)
//          val sa = runSuffixArrayQuery(query)
//          if (sa > wild) {
//            out.println(s"$query $wild $sa")
//            out.flush()
//          }
//          wildcardStats.addValue(wild)
//          saStats.addValue(sa)
//        }

    // calculate statistics
    out.println("name wildcard sa")
    out.println(s"mean ${wildcardStats.getMean} ${saStats.getMean}")
    out.println(s"max ${wildcardStats.getMax} ${saStats.getMax}")
    out.println(s"min ${wildcardStats.getMin} ${saStats.getMin}")
    out.println(s"percentile 50 ${wildcardStats.getPercentile(0.5d)} ${saStats.getPercentile(0.5d)}")
    out.println(s"percentile 60 ${wildcardStats.getPercentile(0.6d)} ${saStats.getPercentile(0.6d)}")
    out.println(s"percentile 70 ${wildcardStats.getPercentile(0.7d)} ${saStats.getPercentile(0.7d)}")
    out.println(s"percentile 80 ${wildcardStats.getPercentile(0.8d)} ${saStats.getPercentile(0.8d)}")
    out.println(s"percentile 90 ${wildcardStats.getPercentile(0.9d)} ${saStats.getPercentile(0.9d)}")
    out.println(s"percentile 95 ${wildcardStats.getPercentile(0.95d)} ${saStats.getPercentile(0.95d)}")
    out.println(s"percentile 99 ${wildcardStats.getPercentile(0.99d)} ${saStats.getPercentile(0.99d)}")
    out.close()
    solr1.close()
    solr2.close()
  }

  def runWildcardQuery(q: String): Int = {
    val query = new SolrQuery(s"text:${q}").addField("id")
    val resp = solr2.query(query)
    //println(s"${resp.getResults.getNumFound} ${resp.getQTime}")
    resp.getQTime
  }

  def runSuffixArrayQuery(q: String): Int = {
    val query = new SolrQuery(s"text:${q}").addField("id")
    val resp = solr1.query(query)
    //println(s"${resp.getResults.getNumFound} ${resp.getQTime}")
    resp.getQTime
  }
}
