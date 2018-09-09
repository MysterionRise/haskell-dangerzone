package org.mystic.codeforces.cf0_100.cf10

import java.util._
import java.io._

object C {

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
    java.lang.Long.parseLong(next)
  }

  def digRoot(x: Int): Int = {
    if (x <= 9) {
      x
    } else {
      var result = 0
      var z = x
      while (z > 9) {
        result += z % 10
        z /= 10
      }
      result += z
      digRoot(result)
    }
  }

  def solver(limit: Int) = {
    Range.inclusive(0, limit).map(x => digRoot(x)).foreach(x => out.print(x + " "))
  }

  def divNumber(x: Int): Long = {
    var C = x
    val sqrt = (Math.sqrt(x) + 1).toInt
    val dividers = new Array[Int](sqrt + 1)
    for (i <- 2 to sqrt) {
      while (C % i == 0) {
        dividers(i) += 1
        C /= i
      }
    }
    var divNumbers: Long = 1L
    for (i <- 1 to sqrt) {
      divNumbers *= (dividers(i) + 1)
    }
    if (C != 1) {
      return 2 * divNumbers
    }
    divNumbers
  }

  def solve = {
    val n = nextInt
    val f = new Array[Long](10)
    val digitRoot: Array[Int] = new Array[Int](1000001)
    for (i <- 0 to 9) {
      digitRoot(i) = i
    }
    for (i <- 1 to n) {
      digitRoot(i) = digitRoot(digitRoot(i / 10) + digitRoot(i % 10))
      f(digitRoot(i)) += 1L
    }
    var X = 0L
    for (da <- 1 to 9) {
      for (db <- 1 to 9) {
        X += (f(da) * f(db) * f(digRoot(da * db)))
      }
    }
    for (divider <- 1 to n) {
        X -= n / divider
    }
    out.println(X)
  }


  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
