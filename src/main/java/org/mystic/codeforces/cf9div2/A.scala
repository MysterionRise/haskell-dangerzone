package org.mystic.codeforces.cf9div2

import java.util._
import java.lang._
import java.io._

object A {

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
    return Long.parseLong(next)
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
