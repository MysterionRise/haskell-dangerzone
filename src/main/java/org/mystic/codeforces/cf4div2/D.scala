package org.mystic.codeforces.cf4div2

import java.util
import java.util._
import java.lang._
import java.io._

import scala.util.Sorting

object D {

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
    return Long.parseLong(next)
  }

//  object EnvelopeOrdering extends PartialOrdering[(Int, Int)] {
//    override def tryCompare(x: (Int, Int), y: (Int, Int)): Option[Int] = {
//      if (x._1 < y._1 && x._2 < y._2) return Some(1)
//      if (x._1 > y._1 && x._2 > y._2) return Some(-1)
//      if (x._1 == y._1 && x._2 == y._2) return Some(0)
//      None
//    }
//
//    override def lteq(x: (Int, Int), y: (Int, Int)): Boolean = x._1 < y._1 && x._2 < y._2
//  }

  def solve = {
    val n = nextInt
    val w = nextInt
    val h = nextInt
    val envelopes = new Array[(Int, Int)](n)
    for (i <- 0 until n) {
      envelopes(i) = (nextInt, nextInt)
    }
//    Sorting.quickSort(envelopes)(EnvelopeOrdering)
    envelopes.foreach(print)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
