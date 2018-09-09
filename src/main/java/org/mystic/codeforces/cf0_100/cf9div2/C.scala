package org.mystic.codeforces.cf0_100.cf9div2

import java.util._
import java.lang._
import java.io._

object C {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = {
    Integer.parseInt(next)
  }

  def nextLong: Long = {
    Long.parseLong(next)
  }

  def f(i: Int): Boolean = {
    val s = String.valueOf(i)
    !s.contains('2') && !s.contains('3') && !s.contains('4') && !s.contains('5') && !s.contains('6') && !s.contains('7') && !s.contains('8') && !s.contains('9')
  }

  def solver(s: Int, e: Int): Int = {
    if (e <= s) return 0
    Range.inclusive(s, e).count(x => f(x))
  }

  def solve = {
    val n = nextInt
//    out.println(solver(1, n))
    val s = String.valueOf(n).toCharArray
    for (i <- 0 until s.length) {
      if (s.charAt(i) == '0' || s.charAt(i) == '1') {}
      else {
        for (j <- i until s.length) {
          s(j) = '1'
        }
      }
    }
    var res = 0.0
    for (i <- 0 until s.length) {
      if (s(i) == '1') {
        res += Math.pow(2, s.length - i - 1)
      }
    }
    out.println(res.toInt)
  }

  def dist(x: Double, y: Double, x1: Double, y1: Double): Double = {
    (x - x1) * (x - x1) + (y - y1) * (y - y1)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
