package scala.org.mystic.codeforces

import java.util._
import java.lang._
import java.io._

/**
 * @author kperikov
 */
object B_332 {

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

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def partialSum(ints: Array[Int]): Array[Long] = {
    val sums = new Array[Long](ints.length)
    sums(0) = ints(0)
    for (i <- 1 until ints.length) {
      sums(i) = sums(i - 1) + ints(i)
    }
    sums
  }

  def sum(ints: Array[Long], x: Int, y: Int): Long = {
    x match {
      case 0 => ints(y)
      case _ => ints(y) - ints(x - 1)
    }
  }

  def solve = {
    val n = nextInt
    val k = nextInt
    val arr = new Array[Int](n)
    for (i <- 0 until n) {
      arr(i) = nextInt
    }
    val partialSums = partialSum(arr)
    var ans = (n - 2 * k, n - k)
    var max = sum(partialSums, n - 2 * k, n - k - 1) + sum(partialSums, n - k, n - 1)
    var suff = (n - k, sum(partialSums, n - k, n - 1))
    for (i <- n - 2 * k - 1 to 0 by -1) {
      var current = sum(partialSums, i + k, i + 2 * k - 1)
      if (current >= suff._2)
        suff = (i + k, current)
      current = sum(partialSums, i, i + k - 1) + suff._2
      if (current >= max) {
        max = current
        ans = (i, suff._1)
      }
    }
    println((ans._1 + 1) + " " + (ans._2 + 1))
  }



}
