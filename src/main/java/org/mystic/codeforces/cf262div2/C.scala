package org.mystic.codeforces.cf262div2

import java.util
import java.util._
import java.io._

object C {

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
    val m = nextInt
    val w = nextInt
    object SegmentTree {

      //      val N = Math.pow(2, 17).toInt
      val N = 8
      val tree = new Array[(Int, Int, Int)](2 * N + 1)

      def build(a: Array[(Int, Int)]): Unit = {
        for (i <- N until tree.length) {
          tree(i) = (a(i - N)._1, a(i - N)._2, 0)
        }
        for (i <- N - 1 to 1 by -1) {
          if (tree(2 * i)._1 < tree(2 * i + 1)._1) {
            tree(i) = (tree(2 * i)._1, tree(2 * i)._2, 0)
          } else {
            tree(i) = (tree(2 * i + 1)._1, tree(2 * i + 1)._2, 0)
          }
        }
      }

      def push(i: Int): Unit = {
        tree(2 * i) = (tree(2 * i)._1, tree(2 * i)._2, tree(2 * i)._3 + tree(i)._3)
        tree(2 * i + 1) = (tree(2 * i + 1)._1, tree(2 * i + 1)._2, tree(2 * i + 1)._3 + tree(i)._3)
        tree(i) = (tree(i)._1, tree(i)._2, 0)
      }

      def inc(i: Int, l: Int, r: Int): Unit = {
        val left = 0
        val right = N - 1
        if (l > r) return
        if (l == left && right == r) {
          tree(i) = (tree(i)._1, tree(i)._2, tree(i)._3 + 1)
          return
        }
        push(i)
        inc(2 * i, l, r)
        inc(2 * i + 1, l, r)
        tree(i) = (Math.min(tree(2 * i)._1 + tree(2 * i)._3, tree(2 * i + 1)._1 + tree(2 * i + 1)._3), tree(i)._2, tree(i)._3)
      }

      def min(l: Int, r: Int): (Int, Int) = {
        var min = Int.MaxValue
        var ind = 0
        var left = l + N
        var right = r + N
        while (left < right) {
          if (left % 2 == 1) {
            if (min > tree(left)._1 + tree(left)._3) {
              ind = tree(left)._2
            }
            min = Math.min(min, tree(left)._1 + tree(left)._3)
            left += 1
          }
          if (right % 2 != 1) {
            if (min > tree(right)._1 + tree(right)._3) {
              ind = tree(right)._2
            }
            min = Math.min(min, tree(right)._1 + tree(right)._3)
            right -= 1
          }
          left /= 2
          right /= 2
        }
        if (left == right) {
          if (min > tree(left)._1 + tree(left)._3) {
            ind = tree(left)._2
          }
          return (Math.min(min, tree(left)._1 + tree(left)._3), ind)
        }
        return (min, ind)
      }
    }
    val flowers = new Array[Int](SegmentTree.N)
    Arrays.fill(flowers, Int.MaxValue)
    for (i <- 0 until n) {
      flowers(i) = nextInt
    }
    SegmentTree.build(flowers.zipWithIndex)
    for (i <- 0 until m) {
      val min = SegmentTree.min(0, n - 1)
      val pos = min._2
      SegmentTree.inc(1, pos, Math.min(pos + w, n - 1))
    }
    out.println(SegmentTree.min(0, n - 1)._1)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
