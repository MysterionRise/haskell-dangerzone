package org.mystic.codeforces.ladder6

import java.io._
import java.util.StringTokenizer

import scala.collection.mutable

object C466 {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

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
    return st.nextToken
  }

  def nextInt: Int = return Integer.parseInt(next)

  def nextLong: Long = return java.lang.Long.parseLong(next)

  def nextDouble: Double = return java.lang.Double.parseDouble(next)

  class MultiHashSet[T <% Comparable[T]] {
    val map = new mutable.HashMap[T, Int]()

    def count(x: T): Int = {
      return map.getOrElse(x, 0)
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
      return true
    }
  }

  def solve: Int = {
    val n = nextInt
    val a = new Array[Long](n)
    val prefixSum = new Array[Long](n)
    val map = new MultiHashSet[Long]
    a(0) = nextInt
    prefixSum(0) = a(0)
    for (i <- 1 until n) {
      a(i) = nextInt
      prefixSum(i) = a(i) + prefixSum(i - 1)
    }
    for (i <- 0 until n - 1) {
      map.add(prefixSum(i))
    }
    var ans = 0L
    for (pos <- 1 until n - 1) {
      val sum = prefixSum(pos - 1)
      map.remove(sum)
      val left = prefixSum(n - 1) - sum
      if (left / 2 == sum && left % 2 == 0) {
        val cnt = map.count(2 * sum)
        ans += cnt
      }
    }
    out.println(ans)
    return 0
  }
}
