package org.mystic.codeforces.cf301_400.cf327div2

import java.io._
import java.util.StringTokenizer

object B {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    return st.nextToken
  }

  def nextInt: Int = return Integer.parseInt(next)

  def nextLong: Long = return java.lang.Long.parseLong(next)

  def nextDouble: Double = return java.lang.Double.parseDouble(next)

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val name = next.toCharArray
    val letters = new Array[Char](27)
    val positions = new Array[Int](n)
    for (i <- 0 until n) {
      positions(i) = name(i) - 'a'
      letters(positions(i)) = name(i)
    }
    for (i <- 0 until m) {
      val x = next.toCharArray.apply(0)
      val y = next.toCharArray.apply(0)
      var ind = -1
      for (j <- 0 until 27) {
        if (letters(j) == x) {
          letters(j) = y
          ind = j
        }
      }
      for (j <- 0 until 27) {
        if (letters(j) == y && j != ind) {
          letters(j) = x
        }
      }
    }
    for (i <- 0 until positions.length) {
      out.print(letters(positions(i)))
    }
    return 0
  }
}
