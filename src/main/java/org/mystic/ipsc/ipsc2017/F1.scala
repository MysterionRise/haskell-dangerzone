package org.mystic.ipsc.ipsc2017

import java.io._
import java.util.{StringTokenizer, TreeMap}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object F1 {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("f1.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("f1.out")))
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

  def isBlack(color: Array[Boolean]) = color.filter(_ == false).length == 0

  def solve = {
    val t = nextInt
    for (_ <- 0 until t) {
      val c = nextInt
      val s = nextInt
      val color = new Array[Boolean](c)
      for (i <- 0 until c) {
        color(i) = true
      }
      var rounds = 0
      var current = 0
      do {
        val newPos = (current + Math.sqrt(s).toInt - 1) % c
        if (current <= newPos) {
          val buff = new ArrayBuffer[Boolean]()
          for (i <- current to newPos) {
            color(i) = !color(i)
            buff.append(color(i))
          }
          var j = buff.length - 1
          for (i <- current to newPos) {
            color(i) = buff(j)
            j -= 1
          }
        } else {
          val buff = new ArrayBuffer[Boolean]()
          for (i <- current until c) {
            color(i) = !color(i)
            buff.append(color(i))
          }
          for (i <- 0 to newPos) {
            color(i) = !color(i)
            buff.append(color(i))
          }
          var j = buff.length - 1
          for (i <- current until c) {
            color(i) = buff(j)
            j -= 1
          }
          for (i <- 0 to newPos) {
            color(i) = buff(j)
            j -= 1
          }
        }
        current = newPos + 1
        rounds += 1
      } while (!isBlack(color))
      out.println(rounds)
    }
  }

}
