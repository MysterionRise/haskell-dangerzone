package org.mystic.codeforces.cf0_100.cf16div2

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
    val n = nextInt
    val m = nextInt
    var prevColor = -1
    var flag = true
    for (i <- 0 until n) {
      val line = next.toCharArray
      val lineColor = line(0)
      for (j <- 1 until m ) {
        if (line(j) != lineColor) {
          flag = false
        }
      }
      if (lineColor == prevColor) {
        flag = false
      }
      prevColor = lineColor
    }
    if (flag) {
      out.println("YES")
    } else {
      out.println("NO")
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
