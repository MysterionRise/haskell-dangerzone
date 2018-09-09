package org.mystic.codeforces.cf201_300.cf276div2

import java.util._
import java.io._
import java.util

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
    val n = nextInt
    var minX = Int.MaxValue
    var minY = Int.MaxValue
    var maxX = Int.MinValue
    var maxY = Int.MinValue
    for (i <- 0 until n) {
      val x = nextInt
      minX = Math.min(minX, x)
      maxX = Math.max(maxX, x)
      val y = nextInt
      minY = Math.min(minY, y)
      maxY = Math.max(maxY, y)
    }
    var sq1: Long = Math.abs(minX - maxX).toLong
    sq1 *= sq1
    var sq2: Long = Math.abs(minY - maxY).toLong
    sq2 *= sq2
    out.println(Math.max(sq1, sq2))

    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
