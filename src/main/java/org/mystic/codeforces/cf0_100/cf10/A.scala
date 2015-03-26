package org.mystic.codeforces.cf0_100.cf10

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
    val p1 = nextInt
    val p2 = nextInt
    val p3 = nextInt
    val t1 = nextInt
    val t2 = nextInt
    var result = 0
    var prev = 0
    for (i <- 0 until n) {
      val l = nextInt
      val r = nextInt
      if (i == 0) {
        prev = l
      }
      result += (r - l) * p1
      if (l - t1 > prev) {
        result += t1 * p1
        if (l - t1 - t2 > prev) {
          result += t2 * p2
          result += (l - t1 - t2 - prev) * p3
        } else {
          result += (l - prev - t1) * p2
        }
      } else {
        result += (l - prev) * p1
      }
      prev = r
    }
    out.println(result)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
