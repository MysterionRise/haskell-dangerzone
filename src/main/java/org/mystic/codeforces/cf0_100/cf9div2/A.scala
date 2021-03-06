package org.mystic.codeforces.cf0_100.cf9div2

import java.util._
import java.lang._
import java.io._

object A {

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
    Long.parseLong(next)
  }

  def solve = {
    val y = nextInt
    val w = nextInt
    val x = 6 - Math.max(y, w) + 1
    if (x % 6 == 0) {
      out.println(x / 6 + "/" + 1)
    } else if (x % 3 == 0) {
      out.println(x / 3 + "/" + 2)
    } else if (x % 2 == 0) {
      out.println(x / 2 + "/" + 3)
    } else {
      out.println(x + "/" + 6)
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
