package org.mystic.codeforces.cf0_100.cf14div2

import java.util._
import java.lang._
import java.io._

object B {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = {
    Integer.parseInt(next)
  }

  def nextLong: Long = {
    Long.parseLong(next)
  }

  def comp(x: (Int, Int), y: (Int, Int)): Boolean = {
    if (x._1 != y._1) x._1 < y._1 else x._2 < y._2
  }

  def solve = {
    val n = nextInt
    val x0 = nextInt
    val ranges = new Array[(Int, Int)](n)
    for (i <- 0 until n) {
      val a = nextInt
      val b = nextInt
      ranges(i) = (Math.min(a, b), Math.max(a, b))
    }
    ranges.sortWith(comp)
    var start = 0
    var finish = Int.MaxValue
    for (i <- 0 until n) {
      start = Math.max(start, ranges(i)._1)
      finish = Math.min(finish, ranges(i)._2)
    }
    if (start > finish) {
      out.println(-1)
    } else {
      var ans = Int.MaxValue
      for (i <- start to finish) {
        if (Math.abs(x0 - i) < ans) {
          ans = Math.abs(x0 - i)
        }
      }
      out.println(ans)
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
