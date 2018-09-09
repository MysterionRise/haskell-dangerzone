package org.mystic.codeforces.cf301_400.cf345div2

import java.io._
import java.util._

import scala.collection.mutable

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

    override def toString = s"MultiHashSet($map)"
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
    val n = nextInt
    val d = new Array[(Int, Int)](n)
    for (i <- 0 until n) {
      d(i) = (nextInt, nextInt)
    }
    var ans = 0L
    val byX = d.sortBy(x => x._1)
    val byY = d.sortBy(x => x._2)
    var tmp = 1L
    var prev = byX(0)._1
    for (i <- 1 until n) {
      if (byX(i)._1 == byX(i - 1)._1) {
        tmp += 1
      } else {
        prev = byX(i)._1
        ans += (tmp * (tmp - 1L)) / 2
        tmp = 1
      }
    }
    ans += (tmp * (tmp - 1L)) / 2
    tmp = 1
    prev = byY(0)._2
    for (i <- 1 until n) {
      if (byY(i)._2 == byY(i - 1)._2) {
        tmp += 1
      } else {
        prev = byY(i)._2
        ans += (tmp * (tmp - 1L)) / 2
        tmp = 1
      }
    }
    ans += (tmp * (tmp - 1L)) / 2
    val sorted = d.sortBy(r => (r._1, r._2))
    var common = 1L
    var ind = 1
    while (ind < n) {
      while (ind < n && sorted(ind)._1 == sorted(ind - 1)._1 && sorted(ind)._2 == sorted(ind - 1)._2) {
        common += 1
        ind += 1
      }
      ans -= (common * (common - 1L)) / 2
      common = 1
      ind += 1
    }
    out.println(ans)
    0
  }
}
