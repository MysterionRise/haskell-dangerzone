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
      SegmentTree.inc(pos, Math.min(pos + w, n - 1))
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
