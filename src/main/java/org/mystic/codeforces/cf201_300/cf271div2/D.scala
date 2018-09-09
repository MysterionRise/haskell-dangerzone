package org.mystic.codeforces.cf201_300.cf271div2

import java.util._
import java.io._
import java.util

object D {

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
    java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val mod = (1e9 + 7).toInt
    val t = nextInt
    val k = nextInt
    val dp = new Array[Int](1e5.toInt + 1)
    dp(0) = 1
    for (i <- 1 until dp.length) {
      if (i == k) {
        dp(i) = 2
      }
      else if (i > k) {
        dp(i) = (dp(i - 1) % mod + dp(i - k) % mod) % mod
      } else {
        dp(i) = 1
      }
    }
    val prefixSum = new Array[Int](1e5.toInt + 1)
    prefixSum(0) = dp(0)
    for (i <- 1 until prefixSum.length) {
      prefixSum(i) = (dp(i) % mod + prefixSum(i - 1) % mod) % mod
    }
    for (i <- 0 until t) {
      val a = nextInt
      val b = nextInt
      out.println((prefixSum(b) - prefixSum(a - 1) + mod) % mod)
    }
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
