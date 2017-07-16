package org.mystic.ipsc.ipsc2015

import java.io._
import java.util._

import scala.collection.mutable

object B1 {

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
    val map1 = mutable.HashMap[String, Int]()
    val map2 = mutable.HashMap[String, Int]()
    val map3 = mutable.HashMap[String, Int]()
    var m = nextInt
    for (j <- 0 until m) {
      map1 += (next -> nextInt)
    }
    m = nextInt
    for (j <- 0 until m) {
      map2 += (next -> nextInt)
    }
    m = nextInt
    for (j <- 0 until m) {
      map3 += (next -> nextInt)
    }

    val i1 = map1.keysIterator

    val superPhrases = new ArrayList[(String, Int)]
    while (i1.hasNext) {
      val a = i1.next()
      val i2 = map2.keysIterator
      while (i2.hasNext) {
        val b = i2.next()
        val i3 = map3.keysIterator
        while (i3.hasNext) {
          val c = i3.next()
          superPhrases.add((a + " " + b + " " + c, map1.getOrElse(a, 0) + map2.getOrElse(b, 0) + map3.getOrElse(c, 0)))
        }
      }
    }
    m = nextInt
    val phrases = new Array[(String, Int, Int)](m)
    for (i <- 0 until m) {
      val a = next
      val b = next
      val c = next
      val str = a + " " + b + " " + c
      val force = map1.getOrElse(a, 0) + map2.getOrElse(b, 0) + map3.getOrElse(c, 0)
      phrases(i) = (str, force, i)
    }
    val ourPhrases = superPhrases.toArray(Array[(String, Int)]()).sortWith(_._2 >= _._2)
    val sorted = phrases.sortWith(_._2 >= _._2)
    val answers = new Array[String](m)
    val set = new mutable.HashSet[Int]()
    for (i <- 0 until sorted.length) {
      val force = sorted(i)._2
      var idx = 0
      var flag = true
      while (flag && idx < ourPhrases.length) {
        if (ourPhrases(idx)._2 - 1 == force && !set.contains(idx)) {
          flag = false
          answers(sorted(i)._3) = ourPhrases(idx)._1 /*+ " " + sorted(i)._2 + " " + ourPhrases(idx)._2*/
          set.add(idx)
        } else {
          idx += 1
        }
      }
    }
    for (i <- 0 until answers.length) {
      out.println(answers(i))
    }
    return 0
  }
}