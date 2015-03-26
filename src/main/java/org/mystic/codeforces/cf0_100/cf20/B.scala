package org.mystic.codeforces.cf0_100.cf20

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
    val a = nextLong
    val b = nextLong
    val c = nextLong
    if (a == 0 && b == 0 && c == 0) {
      out.println(-1)
      return 1
    }
    if (a == 0 && b == 0) {
      out.println(0)
      return 1
    }
    if (a == 0) {
      out.println(1)
      out.println((1.0d * (-c)) / b)
      return 1
    }
    val d = b * b - 4L * a * c
    if (d < 0) {
      out.println(0)
      return 1
    }
    if (d == 0) {
      out.println(1)
      out.println((1.0d * (-b)) / (2.0d * a))
      return 1
    }
    out.println(2)
    out.println(Math.min( ((1.0d * (-b)) - Math.sqrt(d) )/ (2.0d * a), ((1.0d * (-b)) + Math.sqrt(d) )/ (2.0d * a)))
    out.println(Math.max( ((1.0d * (-b)) - Math.sqrt(d) )/ (2.0d * a), ((1.0d * (-b)) + Math.sqrt(d) )/ (2.0d * a)))
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
