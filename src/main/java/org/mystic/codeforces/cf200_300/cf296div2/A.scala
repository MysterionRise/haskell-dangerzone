package org.mystic.codeforces.cf200_300.cf296div2

import java.io._
import java.util
import java.util._

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
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    var a = nextLong
    var b = nextLong
    var cnt = 1L
    while (a != b) {
      val k = (a / b)
      cnt += k
      var a1 = a - k * b
      if (a1 == 0) {
        a1 = b
        cnt -= 1
      }
      val b1 = b
      a = Math.max(a1, b1)
      b = Math.min(a1, b1)
    }
    out.println(cnt)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
