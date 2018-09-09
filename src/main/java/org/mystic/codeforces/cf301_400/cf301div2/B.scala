package org.mystic.codeforces.cf301_400.cf301div2

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

  def solve: Int = {
    val n = nextInt
    val k = nextInt
    val p = nextInt
    val x = nextInt
    val y = nextInt
    val a = new Array[Int](n)
    var sum = 0
    for (i <- 0 until n - k) {
      a(i) = 1
      sum += a(i)
    }
    for (i <- n - k until n) {
      a(i) = nextInt
      sum += a(i)
    }
    val s = a.sorted
    val median = s((n + 1) / 2 - 1)
    if (sum > x) {
      out.println(-1)
      return 1
    } else if (median >= y) {
      for (i <- 0 until n - k) {
        out.print("1 ")
      }
      return 1
    }
    for (i <- 1 to n - k) {
      sum += y - 1
      val z = (n + 1) / 2
      if (z + i >= a.length || a(z + i) >= y) {
        if (sum > x) {
          out.println(-1)
          return 1
        }
        for (j <- i + 1 to n - k) {
          out.print("1 ")
        }
        for (j <- 1 to i) {
          out.print(y + " ")
        }
        return 1
      }
    }
    out.println(-1)
    0
  }
}