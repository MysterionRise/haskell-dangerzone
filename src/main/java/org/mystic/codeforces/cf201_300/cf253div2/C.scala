package org.mystic.codeforces.cf201_300.cf253div2

import java.io._
import java.util
import java.util.{StringTokenizer, TreeMap}

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
    val guessers = Array(49, 50, 51, 52, 53, 66, 71, 82, 87, 89)
    val n = nextInt
    val cards = new mutable.HashSet[(Int, Int)]
    for (i <- 0 until n) {
      val card = next
      cards.add(card(0), card(1))
    }
    if (cards.size == 1) {
      println(0)
      return 0
    }
    val arr = cards.toList.toArray
    var ans = n
    for (i <- 1 to Math.pow(2, 10).toInt) {
      val s = new util.ArrayList[Int]()
      val line = Integer.toBinaryString(i)
      for (j <- 0 until line.length) {
        if (line.charAt(j) == '1') {
          s.add(guessers(j))
        }
      }
      val check = new Array[Int](arr.length)
      for (j <- 0 until s.size()) {
        val guess = s.get(j)
        for (x <- arr.indices) {
          for (y <- arr.indices) {
            if (x != y) {
              val a = arr(x)
              val b = arr(y)
              if ((a._1 != b._1 && a._1 == guess) || (a._2 != b._2 && a._2 == guess)) {
                check(x) += 1
              }
            }
          }
        }
      }
      var flag = true
      for (j <- check.indices) {
        if (check(j) != arr.length - 1) {
          flag = false
        }
      }
      if (flag) {
        ans = Math.min(ans, i)
      }
    }

    println(ans)
    0
  }

}
