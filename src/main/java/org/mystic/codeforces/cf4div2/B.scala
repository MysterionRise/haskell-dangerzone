package org.mystic.codeforces.cf4div2

import java.util._
import java.lang._
import java.io._

object B {

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
    return Long.parseLong(next)
  }

  def solve = {
    val d = nextInt
    val sumTime = nextInt
    val days = new Array[Int](d)
    val minTime = new Array[Int](d)
    val maxTime = new Array[Int](d)
    var sum = 0
    for (i <- 0 until d) {
      minTime(i) = nextInt
      maxTime(i) = nextInt
      days(i) = minTime(i)
      sum += days(i)
    }
    if (sum > sumTime) {
      out.println("NO")
    } else if (sum == sumTime) {
      out.println("YES")
      for (i <- 0 until d) {
        out.print(days(i) + " ")
      }
    } else {
      var diff = sumTime - sum
      var ind = 0
      while (diff > 0 && ind < d) {
        while (days(ind) < maxTime(ind) && diff > 0) {
          days(ind) += 1
          diff -= 1
        }
        ind += 1
      }
      if (diff > 0) {
        out.println("NO")
      } else {
        out.println("YES")
        for (i <- 0 until d) {
          out.print(days(i) + " ")
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
