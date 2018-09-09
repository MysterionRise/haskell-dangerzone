package org.mystic.codeforces.cf201_300.cf260div2

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

  def comp(x: (Int, Int), y: (Int, Int)): Boolean = {
    x._1 > y._1
  }

  def solve: Int = {
    val n = nextInt
    val notebooks = new Array[(Int, Int)](n)
    val a = new Array[Int](n)
    val b = new Array[Int](n)
    for (i <- 0 until n) {
      a(i) = nextInt
      b(i) = nextInt
      notebooks(i) = (a(i), b(i))
    }
    val sorted = notebooks.sortWith(comp)
    var min = sorted(0)._2
    for (i <- 1 until n) {
      if (sorted(i)._2 > min) {
    out.println("Happy Alex")
        out.println("Happy Alex")
        return 1
      }
      min = Math.min(min, sorted(i)._2)
    }
    out.println("Poor Alex")
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
