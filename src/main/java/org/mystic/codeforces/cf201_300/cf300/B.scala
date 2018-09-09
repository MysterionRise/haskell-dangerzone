package org.mystic.codeforces.cf201_300.cf300

import java.io._
import java.util
import java.util._

import scala.collection.mutable

/**
 * ML at test 1 :)
 */
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

  class MultiHashSet[T <% Comparable[T]] {
    val map = new mutable.HashMap[T, Int]()

    def count(x: T): Int = {
      map.getOrElse(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      true
    }
  }

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

    def count(x: T): Int = {
      map.getOrDefault(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def first(): T = map.firstKey()

    def last(): T = map.lastKey()

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      true
    }
  }

  val ans = new util.ArrayList[Int]()

  def findAns(x: Int, y: Int, dp: Array[Array[Int]], weights: Array[Int]): Unit = {
    if (dp(x)(y) == 0)
      return
    if (dp(x - 1)(y) == dp(x)(y)) {
      findAns(x - 1, y, dp, weights)
    } else  {
      findAns(x, y - weights(x - 1), dp, weights)
      ans.add(x - 1)
    }
  }

  def solve: Int = {
    val weights = (1 to 1e6.toInt).filter(x => {
      val s = String.valueOf(x).filter(x => x == '0' || x == '1')
      s.length == String.valueOf(x).length
    }).toArray
    val W = nextInt
    val K = 1e6.toInt + 1
    val dp = new Array[Array[Int]](weights.length + 1)
    for (i <- 0 until weights.length + 1) {
      dp(i) = new Array[Int](K)
    }
    dp(0)(0) = 0
    for (c <- 1 to K - 1)
      dp(0)(c) = Int.MaxValue / 10
    for (i <- 1 to weights.length) {
      for (c <- 0 to weights(i - 1) - 1) {
        dp(i)(c) = dp(i - 1)(c)
      }
      for (c <- weights(i - 1) to W) {
        dp(i)(c) = Math.min(dp(i - 1)(c), dp(i)(c - weights(i - 1)) + 1)
      }
    }
    out.println(dp(weights.length)(W))
    findAns(weights.length, W, dp, weights)
    for (i <- 0 until ans.size()) {
      out.print(weights(ans.get(i)) + " ")
    }
    0
  }
}
