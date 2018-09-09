package org.mystic.codeforces.cf301_400.cf322div2

import java.io._
import java.util._

import scala.collection.mutable

object B {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = Integer.parseInt(next)

  def nextLong: Long = java.lang.Long.parseLong(next)

  def nextDouble: Double = java.lang.Double.parseDouble(next)

  def solve: Int = {
    val n = nextInt
    val h = new Array[Int](n)
    for (i <- 0 until n) {
      h(i) = nextInt
    }
    val max = new Array[Int](n)
    max(n - 1) = h(n - 1)
    for (i <- n - 2 to 0 by -1) {
      max(i) = Math.max(max(i + 1), h(i))
    }
    for (i <- 0 until n - 1) {
      val m = max(i + 1)
      if (h(i) > m) {
        out.print(s"0 ")
      } else if (h(i) == m) {
        out.print(s"1 ")
      } else
        out.print((m - h(i) + 1) + " ")
    }
    out.print("0")
    0
  }
}
