package org.mystic.codeforces.custom.looksery2015

import java.io._
import java.util.{StringTokenizer, TreeMap}

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

  var ft1: Array[Array[Int]] = _
  var ft2: Array[Array[Int]] = _
  var in: Array[Array[Char]] = _
  var bValue: Int = -1
  var wValue: Int = 1

  def invert1(x: Int, y: Int, diff: Int) = {
    for (i <- 0 to x)
      for (j <- 0 to y) {
        ft1(i)(j) += diff
      }
  }

//  def invert2(x: Int, y: Int) = {
//    for (i <- 0 to x)
//      for (j <- 0 to y) {
//        ft2(i)(j) = !ft2(i)(j)
//      }
//  }

  def check1(): Boolean = {
    val n = ft1.length
    val m = ft1(0).length
    for (i <- n - 1 to 0 by -1) {
      for (j <- m - 1 to 0 by -1) {
        if (in(i)(j) == 'B' && ft1(i)(j) != bValue) {
          invert1(i, j, bValue - ft1(i)(j))
          return false
        }
        if (in(i)(j) == 'W' && ft1(i)(j) != wValue) {
          invert1(i, j, wValue - ft1(i)(j))
          return false
        }
      }
    }
    true
  }

//  def check2(): Boolean = {
//    val n = ft2.length
//    val m = ft2(0).length
//    for (i <- n - 1 to 0 by -1) {
//      for (j <- m - 1 to 0 by -1) {
//        if ((in(i)(j) == 'B' && ft2(i)(j) != bValue) || (in(i)(j) == 'W' && ft2(i)(j) != wValue)) {
//          invert2(i, j)
//          return false
//        }
//      }
//    }
//    true
//  }

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    ft1 = new Array[Array[Int]](n)
    ft2 = new Array[Array[Int]](n)
    in = new Array[Array[Char]](n)
    for (i <- 0 until n) {
      ft1(i) = new Array[Int](m)
      ft2(i) = new Array[Int](m)
      in(i) = next.toCharArray
    }
    var ans1 = 0
    while (!check1 && ans1 <= 10000) {
      ans1 += 1
    }
    out.println(ans1)
    return 0
  }
}
