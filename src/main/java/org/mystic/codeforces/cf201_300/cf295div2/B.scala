package org.mystic.codeforces.cf201_300.cf295div2

import java.io._
import java.util
import java.util._

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
    return java.lang.Long.parseLong(next)
  }


  def solve: Int = {
    val n = nextInt
    val m = nextInt
    if (m < n) {
      out.println(n - m)
      return 1
    }
    if (m == n) {
      out.println(0)
      return 1
    }

    val sz = 2 * Math.max(n, m)
    val dp = new Array[Array[Int]](sz + 1)
    for (i <- 0 until sz + 1) {
      dp(i) = new Array[Int](sz + 1)
    }
    for (i <- 0 to sz) {
      for (j <- 1 to sz) {
        if (i > j) {
          dp(i)(j) = i - j
        }
      }
    }
    class Utils {
      def f(n: Int, m: Int, ans: Int): Int = {
        if (n < 0) {
          return 1e6.toInt
        }
        if (n * 4 > m) {
          return 1e6.toInt
        }
        if (n * 4 > m && n - 1 <= 0) {
          return 1e6.toInt
        }
        if (n == m) {
          return ans
        }
        if (n * 2 == m) {
          return ans + 1
        }
        if (n - 1 == m) {
          return ans + 1
        }
        if (n > m) {
          return ans + n - m
        }
        return Math.min(f(n * 2, m, ans + 1), f(n - 1, m, ans + 1))
      }
    }
    val u = new Utils
    out.println(u.f(n, m, 0))
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
