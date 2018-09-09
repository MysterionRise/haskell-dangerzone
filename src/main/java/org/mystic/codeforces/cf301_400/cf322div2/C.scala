package org.mystic.codeforces.cf301_400.cf322div2

import java.io._
import java.util._

import scala.collection.mutable

object C {

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
    var k = nextInt
    val ab = new Array[Int](n)
    val diff = new Array[(Int, Int)](n)
    for (i <- 0 until n) {
      ab(i) = nextInt
      diff(i) = ((ab(i) / 10 + 1) * 10 - ab(i), i)
    }
    val sorted = diff.sortBy(_._1).foreach(x => {
      if (k >= x._1 && ab(x._2) < 100) {
        ab(x._2) += x._1
        k -= x._1
      }
    })
    for (i <- 0 until n) {
      while (k >= 10 && ab(i) != 100) {
        ab(i) += 10
        k -= 10
      }
    }
    var rtg = 0
    for (i <- ab.indices) {
      rtg += ab(i) / 10
    }
    out.println(rtg)
    0
  }
}
