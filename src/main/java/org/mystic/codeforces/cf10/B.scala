package org.mystic.codeforces.cf10

import java.util._
import java.lang._
import java.io._

object B {

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

  def solve = {
    val n = nextInt
    val k = nextInt
    val cinema = new Array[Array[Int]](k)
    for (i <- 0 until k) {
      cinema(i) = new Array[Int](k)
    }
    for (i <- 0 until k) {
      for (j <- 0 until k) {
        cinema(i)(j) = func(k, i, j)
      }
    }
    for (i <- 0 until n) {
      val m = nextInt
      // TODO
    }
  }

  def func(k: Int, x: Int, y: Int) = {
    Math.abs(x - k / 2) + Math.abs(y - k / 2)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
