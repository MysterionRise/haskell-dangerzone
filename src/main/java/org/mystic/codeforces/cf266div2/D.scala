package org.mystic.codeforces.cf266div2

import java.util._
import java.io._
import java.util
import scala.annotation.tailrec

object D {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null
  var ans = 0

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

    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
