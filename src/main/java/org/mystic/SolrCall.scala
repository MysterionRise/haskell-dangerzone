package org.mystic

import java.io.PrintWriter
import java.net.{Authenticator, PasswordAuthentication}
import java.util

import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.client.solrj.{SolrClient, SolrQuery, SolrRequest, SolrResponse}
import org.apache.solr.common.SolrDocument
import org.apache.solr.common.params.CommonParams
import org.apache.solr.common.util.{NamedList, SolrjNamedThreadFactory}
import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, TextNode}
import org.jsoup.select.Elements
import org.mystic.Template.MultiHashSet

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object SolrCall {

  setupProxy

  def main(args: Array[String]) {

    val client = new HttpSolrClient("http://search-daily-e1int.search-aws-cicd.kohls.com:8080/")
    for (i <- 2358000 until 4585206 by 1000) {
      println(i)
      val resp = client.query("products", new SolrQuery("*:*").setRequestHandler("/select").setStart(i).setRows(1000).set(CommonParams.WT, "json"))
      val out = new PrintWriter(s"catalog-$i")
      out.println(scala.util.parsing.json.JSONArray(List(resp.getResults)))
      out.flush()
      out.close()
    }
  }


  def setupProxy: String = {
    System.setProperty("http.proxyHost", "proxy.kohls.com")
    System.setProperty("http.proxyPort", "3128")
  }
}
