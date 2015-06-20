package org.mystic.ipsc2015

import java.io._
import java.util._

import scala.collection.mutable

object B2 {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new FileOutputStream("b1.out"))
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

  def solve: Int = {
    var m = nextInt
    val arr1 = new Array[(String, Int)](m)
    val arr2 = new Array[(String, Int)](m)
    val arr3 = new Array[(String, Int)](m)
    val map1 = mutable.HashMap[String, Int]()
    val map2 = mutable.HashMap[String, Int]()
    val map3 = mutable.HashMap[String, Int]()
    for (j <- 0 until m) {
      arr1(j) = (next, nextInt)
      map1 += (arr1(j)._1 -> arr1(j)._2)
    }
    m = nextInt
    for (j <- 0 until m) {
      arr2(j) = (next, nextInt)
      map2 += (arr2(j)._1 -> arr1(j)._2)
    }
    m = nextInt
    for (j <- 0 until m) {
      arr3(j) = (next, nextInt)
      map3 += (arr3(j)._1 -> arr1(j)._2)
    }
    m = nextInt
    val used = mutable.HashSet[String]()
    for (i <- 0 until m) {
      val a = next
      val b = next
      val c = next
      val str = a + " " + b + " " + c
      val forceA = map1.getOrElse(a, 0)
      val forceB = map2.getOrElse(b, 0)
      val forceC = map3.getOrElse(c, 0)
      var j = 0
      var flag = true
      while (j < arr1.length && flag) {
        if (arr1(j)._2 - 1 == forceA && !used.contains(s"${arr1(j)._1} $b $c")) {
          out.println(s"${arr1(j)._1} $b $c")
          used.add(s"${arr1(j)._1} $b $c")
          flag = false
        }
        j += 1
      }
      j = 0
      while (j < arr2.length && flag) {
        if (arr2(j)._2 - 1 == forceB && !used.contains(s"$a ${arr2(j)._1} $c")) {
          out.println(s"$a ${arr2(j)._1} $c")
          used.add(s"$a ${arr2(j)._1} $c")
          flag = false
        }

        j += 1
      }
      j = 0
      while (j < arr3.length && flag) {
        if (arr3(j)._2 - 1 == forceC && !used.contains(s"$a $b ${arr3(j)._1}")) {
          out.println(s"$a $b ${arr3(j)._1}")
          used.add(s"$a $b ${arr3(j)._1}")
          flag = false
        }

        j += 1
      }
    }

    return 0
  }
}
