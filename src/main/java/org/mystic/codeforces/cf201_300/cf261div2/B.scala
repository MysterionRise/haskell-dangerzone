package org.mystic.codeforces.cf201_300.cf261div2

import java.util._
import java.io._

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
    val b = new Array[Int](n)
    var max = Int.MinValue
    var min = Int.MaxValue
    for (i <- 0 until n) {
      b(i) = nextInt
      max = Math.max(max, b(i))
      min = Math.min(min, b(i))
    }
    var maxN = 0
    var minN = 0
    out.print(Math.abs(max - min) + " ")
    for (i <- 0 until n) {
      if (b(i) == max) {
        maxN += 1
      }
      if (b(i) == min) {
        minN += 1
      }
    }
    if (max != min) {
      out.print(maxN.toLong * minN.toLong)
    } else {
      out.print((maxN.toLong * (maxN.toLong - 1)) / 2)
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
