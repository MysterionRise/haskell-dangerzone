package org.mystic.codeforces.cf6div2

import java.util
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

  def solve = {
    val n = nextInt
    val time = new Array[Int](n)
    val sumTimeA = new Array[Int](n)
    val sumTimeB = new Array[Int](n)
    time(0) = nextInt
    sumTimeA(0) = time(0)
    for (i <- 1 until n) {
      time(i) = nextInt
      sumTimeA(i) = sumTimeA(i - 1) + time(i)
    }
    sumTimeB(0) = time(n - 1)
    for (i <- 1 until n) {
      sumTimeB(i) = sumTimeB(i - 1) + time(n - i - 1)
    }
    var aInd = 0
    var bInd = 0
    var aNum = 0
    var bNum = 0
    while (aNum + bNum != n) {
      if (sumTimeA(aInd) < sumTimeB(bInd)) {
        aInd += 1
        aNum += 1
      } else if (sumTimeA(aInd) > sumTimeB(bInd)) {
        bInd += 1
        bNum += 1
      } else {
        if (aInd == n - bInd - 1) {
          aNum += 1
        } else {
          aInd += 1
          bInd += 1
          aNum += 1
          bNum += 1
        }
      }
    }
    out.println(aNum + " " + bNum)

  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
