package org.mystic.codeforces.gym100101

import java.util._
import java.io._

object A {

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
    val x = nextInt
    val y = nextInt
    val a0 = nextInt
    val m = nextInt
    val z = nextInt
    val t = nextInt
    val b0 = nextInt
    val two16: Int = Math.pow(2, 16).toInt
    val two30: Int = Math.pow(2, 30).toInt

    object SegmentTree {

      val N = 10000000
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
          left >>= 1
          right >>= 1
        }
        if (left == right) {
          return sum + tree(left)
        }
        return sum
      }
    }
    val a = new Array[Int](SegmentTree.N)
    a(0) = a0
    for (i <- 1 until n) {
      a(i) = (((x.toLong * a(i - 1)) % two16 + y) % two16).toInt
    }
    SegmentTree.build(a)
    var sum: Long = 0
    var c0 = b0 % n
    var bi1 = b0
    for (i <- 1 until 2 * m) {
      var zz = ((z.toLong * bi1) % two30 + t) % two30
      if (zz < 0) {
        zz += two30
      }
      var b1 = zz % n
      if (b1 < 0) {
        b1 += n
      }
      val c1 = b1.toInt
      bi1 = zz.toInt
      if (i % 2 == 1) {
        sum += SegmentTree.sum(Math.min(c0, c1), Math.max(c0, c1))
      } else {
        c0 = c1
      }
    }
    out.println(sum)
    return 1
  }

  def main(args: Array[String]): Unit = {
//    br = new BufferedReader(new InputStreamReader(System.in))
//    out = new PrintWriter(new BufferedOutputStream(System.out))
            br = new BufferedReader(new InputStreamReader(new FileInputStream("sum0.in")))
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("sum0.out")))
    solve
    out.close
  }
}
