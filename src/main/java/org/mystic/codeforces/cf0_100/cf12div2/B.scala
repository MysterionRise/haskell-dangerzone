package org.mystic.codeforces.cf0_100.cf12div2

import java.util._
import java.lang._
import java.io._

object B {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _
  val ints = new Array[Int](10)

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

  def findMinExceptZero(): Char = {
    for (i <- 1 until ints.length) {
      if (ints(i) > 0) {
        ints(i) -= 1
        return (i + '0'.toInt).toChar
      }
    }
    'a'
  }

  def findMin(): Char = {
    for (i <- ints.indices) {
      if (ints(i) > 0) {
        ints(i) -= 1
        return (i + '0'.toInt).toChar
      }
    }
    'a'
  }

  def solve = {
    val in = next
    val num = in.toCharArray
    for (i <- 0 until num.length) {
      ints(num(i) - '0') += 1
    }
    val correctAns = new Array[Char](num.length)
    for (i <- 0 until num.length) {
      if (i == 0) {
        correctAns(i) = findMinExceptZero
      } else {
        correctAns(i) = findMin
      }
    }
    val in2 = next
    val ans = in2.toCharArray
    if (in == "0" && in2 == "0") {
      out.println("OK")
    } else {
      var eq = 0
      for (i <- 0 until Math.min(ans.length, correctAns.length)) {
        if (ans(i) == correctAns(i)) {
          eq += 1
        }
      }
      if (eq == correctAns.length) {
        out.println("OK")
      } else {
        out.println("WRONG_ANSWER")
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
