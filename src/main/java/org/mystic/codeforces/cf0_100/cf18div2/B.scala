package org.mystic.codeforces.cf0_100.cf18div2

import java.util._
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
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextLong
    val d = nextLong
    val m = nextLong
    val l = nextLong
    var pos: Long = 1L * d
    while (pos % m <= l && pos / m < n) {
      val num = pos / m
      val jumps = (num * m + l - pos) / d
      if (jumps == 0) {
        pos += d
      } else {
        pos += jumps * d
      }
    }
    out.println(pos)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
