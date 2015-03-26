package org.mystic.codeforces.cf0_100.cf16div2

import java.util._
import java.lang._
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
    return Long.parseLong(next)
  }

  def bruteForse(a: Long, b: Long, x: Long, y: Long): (Long, Long) = {
    var i = a
    var j = b
    var maxSq: Long = Int.MinValue
    var ans: (Long, Long) = (0, 0)
    while (i > 0) {
      j = b
      while (j > 0) {
        if (i % x == 0 && i / x == j / y && j % y == 0) {
          if (i * j > maxSq) {
            maxSq = i * j
            ans = (i, j)
          }
        }
        j -= 1
      }
      i -= 1
    }
    return ans
  }


  def gcd(a: Long, b: Long): Long = {
    if (b == 0) {
      return a
    }
    return gcd(b, a % b)
  }

  def cleverSolver(a: Long, b: Long, x1: Long, y1: Long): (Long, Long) = {
    val x = x1 / gcd(x1, y1)
    val y = y1 / gcd(x1, y1)
    if (a % x == 0 && a / x == b / y && b % y == 0) {
      return (a, b)
    } else {
      val ax: Double = a / x
      val by: Double = b / y
      val min = Math.min(ax, by)
      val max = Math.max(ax, by)
      val sq = Math.max(min * x * min * y, max * max * x * y)
      if (sq == 0) {
        return (0, 0)
      } else {
        if (max * x <= a && max * y <= b) {
          return ((max * x).toInt, (max * y).toInt)
        } else {
          return ((min * x).toInt, (min * y).toInt)
        }
      }
    }
  }

  def solve = {
    val a = nextLong
    val b = nextLong
    val x = nextLong
    val y = nextLong
    val ans = cleverSolver(a, b, x, y)
    //    val ans = bruteForse(a, b, x, y)
    out.println(ans._1 + " " + ans._2)
    //    out.println("START")
    //    out.flush()
    //    val rand = new Random()
    //    for (i <- 0 to 100000) {
    //      val a = rand.nextInt(500) + 1
    //      val b = rand.nextInt(500) + 1
    //      val x = rand.nextInt(10) + 1
    //      val y = rand.nextInt(10) + 1
    //      val correctAns = bruteForse(a, b, x, y)
    //      val cleverAns = cleverSolver(a, b, x, y)
    //      if (cleverAns != correctAns) {
    //        out.println(a + " " + b + " " + x + " " + y)
    //        out.println("bruteforce = " + correctAns)
    //        out.println("smart = " + cleverAns)
    //        out.flush()
    //      }
    //    }

  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}