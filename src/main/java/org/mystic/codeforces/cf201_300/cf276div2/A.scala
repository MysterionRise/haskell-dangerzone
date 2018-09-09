package org.mystic.codeforces.cf201_300.cf276div2

import java.util._
import java.io._
import java.util

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
    java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val a = nextInt
    val m = nextInt
    if (a == m) {
      out.println("Yes")
      return 1
    }
    if (m / a % 2 == 0) {
      out.println("Yes")
    } else {
      out.println("No")
    }

    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
