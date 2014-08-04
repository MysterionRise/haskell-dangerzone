package org.mystic.codeforces.cf14div2

import java.util._
import java.io._

object D {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    return st.nextToken
  }

  def nextInt: Int = {
    return java.lang.Integer.parseInt(next)
  }

  def nextLong: Long = {
    return java.lang.Long.parseLong(next)
  }

  def addEdge(edge: (Int, Int), graph: Array[List[Int]]): Array[List[Int]] = {
    graph(edge._1).add(edge._2)
    return graph
  }

  def buildGraphWithoutEdge(edges: Array[(Int, Int)], missedEdgeNumber: Int): Array[List[Int]] = {
    var graph = new Array[List[Int]](edges.length - 1)
    for (i <- 0 until graph.length) {
      graph(i) = new ArrayList[Int]
    }
    for (i <- 0 until edges.length) {
      if (i != missedEdgeNumber) {
        graph = addEdge(edges(i), graph)
      }
    }
    return graph
  }

  def solve = {
    val n = nextInt
    val edges = new Array[(Int, Int)](n - 1)
    for (i <- 0 until n - 1) {
      edges(i) = (nextInt - 1, nextInt - 1)
    }
    for (i <- 0 until n - 1) {
      val graph: Array[List[Int]] = buildGraphWithoutEdge(edges, i)
      var max
    }

  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
