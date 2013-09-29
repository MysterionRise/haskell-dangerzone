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
    val n = nextInt
    val m = nextInt
    val k = nextInt
    val g = new Array[Array[Int]](n + m + 2)
    for (i <- 0 until n + m + 2) {
      g(i) = new Array[Int](n + m + 2)
    }
    for (i <- 0 until k) {
      g(nextInt)(n + nextInt) = 1
    }
    for (i <- 1 to n) {
      g(i)(0) = 1 // add fake source
    }
    for (i <- n + 1 to m) {
      g(i)(n + m - 1) = 1 // add fake sink
    }
    // @todo find max flow
    // answer should be n + m - maxflow
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
