package org.mystic.codeforces.cf301_400.cf327div2

import java.io._
import java.util.StringTokenizer

import scala.util.Random

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
    val rnd = new Random
    for (i <- 0 until 10) {
      val n = 50
      val seq = new Array[Int](n)
      for (j <- 0 until n) {
        seq(j) = rnd.nextInt(2)
      }
      println(seq.mkString(""))
      //      seq(0) = 0
      //      seq(1) = 1
      //      seq(2) = 0
      //      for (j <- 0 until n by 2) {
      //        seq(j) = 0
      //        seq(j + 1) = 1
      //      }
      //      val str = next.toCharArray
      //      val seq = new Array[Int](str.length)
      //      val n = str.length
      //      for (j <- 0 until str.length) {
      //        seq(j) = str(j) - '0'
      //      }
      //      seq(n - 2) = 1
      //      seq(n - 1) = 0
      var changed = true
      var cnt = 0
      while (changed) {
        val seq1 = new Array[Int](n)
        seq1(0) = seq(0)
        seq1(n - 1) = seq(n - 1)
        for (j <- 1 to n - 2) {
          val sum = seq(j - 1) + seq(j) + seq(j + 1)
          if (sum >= 2) {
            seq1(j) = 1
          } else {
            seq1(j) = 0
          }
        }
        cnt += 1
        changed = false
        for (j <- 0 until n) {
          if (seq(j) != seq1(j)) {
            changed = true
          }
        }
        for (j <- 0 until n) {
          seq(j) = seq1(j)
        }
      }
//      println(cnt)
      //      println(s"became stable in $cnt generations")
      println(seq.mkString(""))
      println
    }
    0
  }
}
