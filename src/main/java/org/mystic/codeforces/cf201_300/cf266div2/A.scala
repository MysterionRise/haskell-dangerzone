package org.mystic.codeforces.cf201_300.cf266div2

import java.util._
import java.io._
import java.util
import scala.annotation.tailrec

object A {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _
  var ans = 0

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
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
