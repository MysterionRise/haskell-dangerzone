package org.mystic.codeforces.cf0_100.cf14div2

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

  def solve = {
    val n = nextInt
    val m = nextInt
    val f = new Array[Array[Char]](n)
    for (i <- 0 until n) {
      f(i) = new Array[Char](m)
      f(i) = next.toCharArray
    }
    var left1 = (-1, -1)
    var right1 = (-1, -1)
    for (i <- 0 until n) {
      for (j <- 0 until m) {
        if (f(i)(j) == '*' && left1._1 == -1) {
          left1 = (i, j)
        }
        if (f(i)(j) == '*') {
          right1 = (i, j)
        }
      }
    }
    var left2 = (-1, -1)
    var right2 = (-1, -1)
    for (j <- 0 until m) {
      for (i <- 0 until n) {
        if (f(i)(j) == '*' && left2._1 == -1) {
          left2 = (i, j)
        }
        if (f(i)(j) == '*') {
          right2 = (i, j)
        }
      }
    }
    for (i <- Math.min(left1._1, left2._1) to Math.max(right1._1, right2._1)) {
      for (j <- Math.min(left1._2, left2._2) to Math.max(right1._2, right2._2)) {
        out.print(f(i)(j))
      }
      out.println
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
