package org.mystic.codeforces.cf0_100.cf18div2

import java.util._
import java.io._

object B {

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
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
