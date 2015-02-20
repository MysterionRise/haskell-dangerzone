package org.mystic.codeforces.cf292div2

import java.io._
import java.util._

object E {

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

  def nextLong: Long = {
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val d = new Array[Int](n)
    val h = new Array[Int](n)
    for (i <- 0 until n) {
      d(i) = nextInt
    }
    for (i <- 0 until n) {
      h(i) = nextInt
    }
    for (i <- 0 until m) {
      val a = nextInt
      val b = nextInt

    }
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
