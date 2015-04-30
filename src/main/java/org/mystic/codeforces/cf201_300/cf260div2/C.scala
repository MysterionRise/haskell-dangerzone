package org.mystic.codeforces.cf201_300.cf260div2

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
    val num = new Array[Long](100005)
    for (i <- 0 until n) {
      a(i) = nextInt
      num(a(i)) += 1
    }
    val dp = new Array[Long](100005)
    Arrays.fill(dp, 0L)
    dp(1) = num(1)
    var i = 2
    while (i <= 100002) {
      dp(i) = Math.max(dp(i - 1), dp(i - 2) + num(i) * i.toLong)
      i += 1
    }
    out.println(dp(100002))
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}