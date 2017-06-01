package org.mystic.codeforces.cf401_500.cf417div2

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

  def containsOn(chars: Array[Char]): Boolean = chars.indexOf('1') != -1

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val arr = new Array[Array[Char]](n + 1)
    var count = 0
    for (i <- 1 to n) {
      arr(i) = next.toCharArray
      for (j <- 0 until arr(i).length) {
        if (arr(i)(j) == '1')
          count += 1
      }
    }

    var ans = 0
    var x = 0
    var y = n
    var height = 0
    while (count > 0 && y > 0) {
      if (y > 0 && containsOn(arr(y))) {
        ans += height
        height = 0
        val last = arr(y).lastIndexOf('1')
        val first = arr(y).indexOf('1')
        if (x <= first && x < last) {
          for (i <- x + 1 to last) {
            ans += 1
            x += 1
            if (arr(y)(i) == '1') {
              count -= 1
            }
          }
        } else {
          for (i <- x - 1 to first by -1) {
            ans += 1
            x -= 1
            if (arr(y)(i) == '1') {
              count -= 1
            }
          }
        }
        if (y == 1) {
          out.println(ans)
          return 0
        }
        // decide which ladder to use
        var lastUp = arr(y - 1).lastIndexOf('1')
        var firstUp = arr(y - 1).indexOf('1')
        height = 0
        while (lastUp < 0 && firstUp < 0 && y > 2) {
          height += 1
          y -= 1
          lastUp = arr(y - 1).lastIndexOf('1')
          firstUp = arr(y - 1).indexOf('1')
        }
        if (count == 0) {
          out.println(ans)
          return 0
        }
        if (firstUp + x + 1 + height < m + 2 - lastUp + m + 2 - x - 1 + height) {
          //we are going left
          ans += firstUp + x + 1 + height
          x = firstUp
          y -= 1
          count -= 1
        } else {
          // we are going right
          ans += m + 2 - lastUp + m + 2 - x - 1 + height
          x = lastUp
          y -= 1
          count -= 1
        }
        height = 0
      } else {
        y -= 1
        height += 1
      }
    }
    out.println(ans)
    return 0
  }
}
