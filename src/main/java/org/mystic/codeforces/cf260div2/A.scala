package org.mystic.codeforces.cf260div2

import java.util._
import java.lang._
import java.io._

object A {

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
    return Long.parseLong(next)
  }

  def comp(x: (Int, Int), y: (Int, Int)): Boolean = {
    x._1 < y._1
  }

  def solve: Int = {
    val n = nextInt
    val notebooks = new Array[(Int, Int)](n)
    val a = new Array[Int](n)
    val b = new Array[Int](n)
    for (i <- 0 until n) {
      a(i) = nextInt
      b(i) = nextInt
      notebooks(i) = (a(i), b(i))
    }
    out.println("Happy Alex")
    out.println("Poor Alex")
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
