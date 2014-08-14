package org.mystic.codeforces.cf18div2

import java.util._
import java.io._

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
    val n = nextInt
    val a = new Array[Int](n)
    var sum2 = 0
    for (i <- 0 until n) {
      a(i) = nextInt
      sum2 += a(i)
    }
    var sum1 = 0
    var ans = 0
    for (i <- 1 until n) {
      sum1 += a(i - 1)
      sum2 -= a(i - 1)
      if (sum1 == sum2) {
        ans += 1
      }
    }
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
