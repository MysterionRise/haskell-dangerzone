package org.mystic.codeforces.cf401_500.cf417div2

import java.io._
import java.util._

import scala.collection.mutable

object A {

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

  def solve: Int = {
    val l1 = nextBoolean
    val s1 = nextBoolean
    val r1 = nextBoolean
    val p1 = nextBoolean
    val l2 = nextBoolean
    val s2 = nextBoolean
    val r2 = nextBoolean
    val p2 = nextBoolean
    val l3 = nextBoolean
    val s3 = nextBoolean
    val r3 = nextBoolean
    val p3 = nextBoolean
    val l4 = nextBoolean
    val s4 = nextBoolean
    val r4 = nextBoolean
    val p4 = nextBoolean
    val p13 = p1 || p3
    val p24 = p2 || p4
    // straight
    if ((s1 && p13) || (s3 && p13)) {
      out.println("YES")
      return 0
    }
    if ((s2 && p24) || (s4 && p24)) {
      out.println("YES")
      return 0
    }
    //right
    if (r1 && (p1 || p2)) {
      out.println("YES")
      return 0
    }
    if (r2 && (p2 || p3)) {
      out.println("YES")
      return 0
    }
    if (r3 && (p3 || p4)) {
      out.println("YES")
      return 0
    }
    if (r4 && (p4 || p1)) {
      out.println("YES")
      return 0
    }

    //left
    if (l1 && (p1 || p4)) {
      out.println("YES")
      return 0
    }
    if (l2 && (p2 || p1)) {
      out.println("YES")
      return 0
    }
    if (l3 && (p3 || p2)) {
      out.println("YES")
      return 0
    }
    if (l4 && (p4 || p3)) {
      out.println("YES")
      return 0
    }
    out.println("NO")
    return 0
  }
}
