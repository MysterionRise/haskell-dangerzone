package org.mystic.codeforces.cf16div2

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

  def bruteForse(a: Int, b: Int, x: Int, y: Int): (Int, Int) = {
    var i = a
    var j = b
    var maxSq = Int.MinValue
    var ans = (0, 0)
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

  def cleverSolver(a: Int, b: Int, x: Int, y: Int): (Int, Int) = {
    var la = 0
    var ra = a
    var lb = 0
    val rb = b

    if (a % x == 0 && a / x == b / y && b % y == 0) {
      return (a.toInt, b.toInt)
    } else {
      val ax: Double = a / x
      val by: Double = b / y
      val min = Math.min(ax, by)
      val max = Math.max(ax, by)
      if (max * x <= a && max * y <= b && (max * x) * y == (max * y) * x) {
        if (max * x < 1.0 || max * y < 1.0) {
          return (0, 0)
        }
        return ((max * x).toInt, (max * y).toInt)
      } else if (min * x <= a && min * y <= b && (min * x) * y == (min * y) * x) {
        if (min * x < 1d || min * y < 1d) {
          return (0, 0)
        }
        return ((min * x).toInt, (min * y).toInt)
      } else {
        return (0, 0)
      }
    }
  }

  def solve = {
    val a = nextLong.toDouble
    val b = nextLong.toDouble
    val x = nextLong.toDouble
    val y = nextLong.toDouble
    val ans = cleverSolver(a, b, x, y)
    //    val ans = bruteForse(a, b, x, y)
    out.println(ans._1.toInt + " " + ans._2.toInt)
    //    out.println("START")
    //    out.flush()
    //    val rand = new Random()
    //    for (i <- 0 to 100000) {
    //      val a = rand.nextInt(500) + 1
    //      val b = rand.nextInt(500) + 1
    //      val x = rand.nextInt(5000) + 1
    //      val y = rand.nextInt(5000) + 1
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
