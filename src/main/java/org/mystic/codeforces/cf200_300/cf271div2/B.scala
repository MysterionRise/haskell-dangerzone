package org.mystic.codeforces.cf200_300.cf271div2

import java.util._
import java.io._
import java.util

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
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val worms = new Array[Int](n)
    val left = new Array[Int](n)
    val right = new Array[Int](n)
    worms(0) = nextInt
    left(0) = 1
    right(0) = worms(0)
    for (i <- 1 until n) {
      worms(i) = nextInt
      left(i) = right(i - 1) + 1
      right(i) = worms(i) + right(i - 1)
    }
//    left.foreach(x => out.print(x + " "))
//    out.println()
//    right.foreach(x => out.print(x + " "))
//    out.println()
    val m = nextInt
    for (i <- 0 until m) {
      val q = nextInt
      val l = Arrays.binarySearch(left, q)
      if (l >= 0) {
        out.println(l + 1)
      } else {
        val ins = -l - 1
        if (ins - 1 >= 0 && right(ins - 1) >= q && left(ins - 1) <= q) {
          out.println(ins)
        } else if (ins < n && right(ins) >= q && left(ins) <= q) {
          out.println(ins + 1)
        } else if (ins + 1 < n && right(ins + 1) >= q && left(ins + 1) <= q) {
          out.println(ins + 2)
        }
      }
    }
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}