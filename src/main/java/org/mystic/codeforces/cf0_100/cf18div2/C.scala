package org.mystic.codeforces.cf0_100.cf18div2

import java.util._
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
    java.lang.Long.parseLong(next)
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
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
