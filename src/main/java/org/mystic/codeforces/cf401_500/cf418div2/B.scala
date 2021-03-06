package org.mystic.codeforces.cf401_500.cf418div2

import java.io._
import java.util._

import scala.collection.mutable

object B {

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

  def nextBoolean: Boolean = next.equalsIgnoreCase("1")

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

    override def toString = s"MultiHashSet($map)"
  }

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

    def count(x: T): Int = {
      map.getOrDefault(x, 0)
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
    *
    * @param values      Array of Int
    * @param commutative function like min, max, sum
    * @param zero        zero value - e.g. 0 for sum, Inf for min, max
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

  def test(ans: Array[Int], a: Array[Int]): Boolean = {
    var diff = 0
    for (i <- 0 until ans.length) {
      if (ans(i) != a(i)) {
        diff += 1
      }
    }
    diff == 1
  }

  def solve: Int = {
    val n = nextInt
    val a = new Array[Int](n)
    val b = new Array[Int](n)
    for (i <- 0 until n) {
      a(i) = nextInt
    }
    val set = new mutable.HashSet[Int]()
    for (i <- 0 until n) {
      b(i) = nextInt
      set += (i + 1)
    }
    val ans = new Array[Int](n)
    for (i <- 0 until n) {
      if (a(i) == b(i)) {
        ans(i) = a(i)
        set -= (ans(i))
      }
    }
    val firstPos = ans.indexOf(0)
    val remains = set.iterator.toList
    if (set.size == 1) {
      ans(firstPos) = remains.head
    } else if (set.size == 2) {
      ans(firstPos) = remains.head
      val lastPos = ans.lastIndexOf(0)
      ans(lastPos) = remains.last
      if (test(ans, a) && test(ans, b)) {
      } else {
        ans(firstPos) = remains.last
        ans(lastPos) = remains.head
      }
    }
    ans.foreach(x => print(s"$x "))
    return 0
  }
}
