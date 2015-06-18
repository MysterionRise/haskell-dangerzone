package org.mystic.codeforces.cf301_400.cf308div2

import java.io._
import java.util._

import scala.collection.mutable

object D {

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

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

    def count(x: T): Int = {
      val res = map.get(x)
      if (res == null)
        return 0
      return res
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def first(): T = return map.firstKey()

    def last(): T = return map.lastKey()

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

  /**
   * Segment tree for any commutative function
   * @param values Array of Int
   * @param commutative function like min, max, sum
   * @param zero zero value - e.g. 0 for sum, Inf for min, max
   */
  class SegmentTree(values: Array[Int])(commutative: (Int, Int) => Int)(zero: Int) {
    private val SIZE = 1e5.toInt
    private val n = values.length
    val t = new Array[Int](2 * n)
    Array.copy(values, 0, t, n, n)

    // build segment tree
    def build = {
      for (i <- n - 1 until 0 by -1) {
        t(i) = commutative(t(2 * i), t(2 * i + 1))
      }
    }

    // change value at position p to x
    // TODO beatify
    def modify(p: Int, x: Int) = {
      var pos = p + n
      t(pos) = x
      while (pos > 1) {
        t(pos / 2) = commutative(t(pos), t(pos ^ 1))
        pos /= 2
      }
    }

    // TODO implement me!
    def modify(p: Int, left: Int, right: Int) = ???

    def query(p: Int) = ???

    // sum [l, r)
    // min l = 0
    // max r = n
    // TODO beatify
    def query(left: Int, right: Int): Int = {
      var res = zero
      var r = right + n
      var l = left + n
      while (l < r) {
        if (l % 2 == 1) {
          res = commutative(res, t(l))
          l += 1
        }
        if (r % 2 == 1) {
          r -= 1
          res = commutative(res, t(r))
        }
        l /= 2
        r /= 2
      }
      res
    }
  }

  def binomialCoefficient(n: Int, k: Int): Long = {
    var c = 1L
    for (i <- 0 until k) {
      c = c * (n - i) / (i + 1)
    }
    return c
  }

  def phi(x1: Int, y1: Int, x0: Int, y0: Int): Double = {
    val x = x1 - x0
    val y = y1 - y0
    (x, y) match {
      case (x, y) if x > 0 && y >= 0 => Math.atan(y.toDouble / x.toDouble)
      case (x, y) if x > 0 && y < 0 => Math.atan(y.toDouble / x.toDouble) + Math.PI * 2.0d
      case (x, y) if x < 0 => Math.atan(y.toDouble / x.toDouble) + Math.PI
      case (x, y) if x == 0 && y > 0 => Math.PI / 2.0d
      case (x, y) if x == 0 && y < 0 => (3.0d * Math.PI) / 2.0d
      case (x, y) if x == 0 && y == 0 => 0.0d
    }
  }

  def area(x1: Int, y1: Int, x2: Int, y2: Int) = x1 * y2 - y1 * x2

  def solve: Int = {
    val n = nextInt
    val dots = new Array[(Int, Int)](n)
    for (i <- 0 until n) {
      val a = nextInt
      val b = nextInt
      dots(i) = (a, b)
    }
    var ans = 0L
    for (i <- 0 until n) {
      val sorted = dots.sortBy((a: (Int, Int)) => {
        phi(a._1, a._2, dots(i)._1, dots(i)._2)
      })
      sorted.foreach(x => print(x + " "))
      println()
      for (j <- 1 until n) {

      }

      val x1 = dots(i)._1
      val y1 = dots(i)._2
    }
    out.println(binomialCoefficient(n, 3) - ans)
    return 0
  }
}
