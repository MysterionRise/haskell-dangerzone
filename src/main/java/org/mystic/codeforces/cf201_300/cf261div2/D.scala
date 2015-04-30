package org.mystic.codeforces.cf201_300.cf261div2

import java.util._
import java.io._

object D {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    return st.nextToken
  }

  def nextInt: Int = {
    return Integer.parseInt(next)
  }

  def nextLong: Long = {
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val a = new Array[Int](n)
    for (i <- 0 until n) {
      a(i) = nextInt
    }
    if (n == 1) {
      out.println(0)
      return 1
    }
    val f1i = new Array[Int](n - 1)
    val map = scala.collection.mutable.Map[Int, Int]()
    f1i(0) = 1
    map.put(a(0), 1)
    for (i <- 1 to n - 2) {
      val num = map.getOrElse(a(i), 0) + 1
      map.put(a(i), num)
      f1i(i) = num
    }
    map.clear
    val fjn = new Array[Int](n - 1)
    fjn(n - 2) = 1
    map.put(a(n - 1), 1)
    for (i <- n - 3 to 0 by -1) {
      val num = map.getOrElse(a(i + 1), 0) + 1
      map.put(a(i + 1), num)
      fjn(i) = num
    }
    map.clear
    object SegmentTree {

      val N = Math.pow(2, 21).toInt
      //      val N = 8
      val tree = new Array[Int](2 * N)

      def build(a: Array[Int]): Unit = {
        for (i <- N until tree.length) {
          tree(i) = a(i - N)
        }
        for (i <- N - 1 to 1 by -1) {
          tree(i) = tree(2 * i) + tree(2 * i + 1)
        }
      }

      def sum(l: Int, r: Int): Int = {
        var sum = 0
        var left = l + N
        var right = r + N
        while (left < right) {
          if (left % 2 == 1) {
            sum += tree(left)
            left += 1
          }
          if (right % 2 != 1) {
            sum += tree(right)
            right -= 1
          }
          left /= 2
          right /= 2
        }
        if (left == right) {
          return sum + tree(left)
        }
        return sum
      }

      def dec(pos: Int): Unit = {
        var i = pos + N
        while (i > 0) {
          tree(i) -= 1
          i /= 2
        }
      }
    }
    val ranges = new Array[Int](SegmentTree.N)
    for (i <- 0 until fjn.length) {
      ranges(fjn(i)) += 1
    }
    SegmentTree.build(ranges)
    var ans: Long = 0
    for (i <- 0 until f1i.length) {
      ans += SegmentTree.sum(0, f1i(i) - 1)
      SegmentTree.dec(fjn(i))
    }
    out.println(ans)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
