package org.mystic.acm.timus

import java.io._
import java.util.StringTokenizer

object Conference {
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
    return Integer.parseInt(next)
  }

  def solve = {
    val m = nextInt
    val n = nextInt
    val k = nextInt
    val g = createGraph(n + m + 2)
    for (i <- 0 until k) {
      val a = nextInt
      val b = nextInt
      g(a) = (b + m) :: g(a)
    }
    for (i <- 1 to m) {
      g(0) = i :: g(0) // add fake source
    }
    for (i <- m + 1 to m + n) {
      g(i) = (n + m + 1) :: g(i) // add fake sink
    }
    out.println(n + m - maxFlow(0, n + m + 1, g))
  }

  def createGraph(size: Int): Array[List[Int]] = {
    val graph = new Array[List[Int]](size)
    for (i <- 0 until size) {
      graph(i) = List()
    }
    graph
  }


  def maxFlow(s: Int, t: Int, graph: Array[List[Int]]): Int = {
    var flow = 0
    flow
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

}
