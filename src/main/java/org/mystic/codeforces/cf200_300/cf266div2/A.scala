package org.mystic.codeforces.cf200_300.cf266div2

import java.util._
import java.io._
import java.util
import scala.annotation.tailrec

object A {

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
    val n = nextInt
    val m = nextInt
    val a = nextInt
    val b = nextInt
    var min = Int.MaxValue
    for (i <- 0 to n / m + 1) {
      var temp = i * b
      val x: Int = n - (i * m)
      if ( x > 0) {
        temp += x * a
      }
      min = Math.min(min, temp)
    }
    out.println(min)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
