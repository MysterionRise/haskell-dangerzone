package org.mystic.codeforces.cf288div2

import java.io._
import java.util._

object C {

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
    val m = nextInt
    val t = nextInt
    val r = nextInt
    if (r - 1 > t) {
      out.println(-1)
      return 1
    }
    val w = new Array[Int](m)
    for (i <- 0 until m) {
      w(i) = nextInt + t + 1
    }
    var ans = 0
    val candles = new Array[Int](1000)
    for (i <- 0 until m) {
      var sum = 0
      for (j <- w(i) - 1 to w(i) - t  by -1) {
        sum += candles(j)
      }
      if (sum != r) {
        for (j <- w(i) - 1 to w(i) - t by -1) {
          if (sum < r && candles(j) == 0) {
            candles(j) = 1
            sum += 1
          }
        }
        if (sum < r) {
          out.println(-1)
          return 1
        }
      }
    }
    candles.foreach(ans += _)
    out.println(ans)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
