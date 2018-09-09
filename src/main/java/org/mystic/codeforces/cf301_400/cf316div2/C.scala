package org.mystic.codeforces.cf301_400.cf316div2

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

  def solveBruteForce(str1: Array[Char], pos: Int, ch: Char) = {
    val str = new Array[Char](str1.length)
    Array.copy(str1, 0, str, 0, str1.length)
    str(pos) = ch
    var cnt = 0
    var fx = 0
    for (i <- str.indices) {
      if (str(i) == '.') {
        cnt += 1
      } else {
        if (cnt != 0) {
          fx += cnt - 1
        }
        cnt = 0
      }
    }
    if (cnt > 0) {
      fx += cnt - 1
    }
    fx
  }

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val str = next.toCharArray
    var fx = 0
    var cnt = 0
    for (i <- 0 until n) {
      if (str(i) == '.') {
        cnt += 1
      } else {
        if (cnt != 0) {
          fx += cnt - 1
        }
        cnt = 0
      }
    }
    if (cnt > 0) {
      fx += cnt - 1
    }
    for (i <- 0 until m) {
      val pos = nextInt - 1
      val ch = next.toCharArray()(0)
      if (n == 1) {
        out.println(0)
      } else {
//        out.print(solveBruteForce(str, pos, ch) + " ")
        if (ch != str(pos)) {
          if (ch == '.') {
            if (pos == 0) {
              if (str(1) == '.')
                fx += 1
            } else if (pos == n - 1) {
              if (str(n - 2) == '.')
                fx += 1
            } else {
              var c = 0
              if (str(pos - 1) == '.') {
                c += 1
              }
              if (str(pos + 1) == '.') {
                c += 1
              }
              fx += c
            }
          } else if (str(pos) == '.'){
            if (pos == 0) {
              if (str(1) == '.')
                fx -= 1
            } else if (pos == n - 1) {
              if (str(n - 2) == '.')
                fx -= 1
            } else {
              var c = 0
              if (str(pos - 1) == '.') {
                c -= 1
              }
              if (str(pos + 1) == '.') {
                c -= 1
              }
              fx += c
            }
          }
        }
        if (fx < 0) {
          fx = 0
        }
        str(pos) = ch
        out.println(fx)
      }
    }
    0
  }
}
