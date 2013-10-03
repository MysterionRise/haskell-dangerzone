package org.mystic.codeforces.gym100229

import java.io._
import java.util.StringTokenizer

object L {
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

  def solve = {
    val n = nextInt
    val m = nextInt
    val cities = new Array[Int](n)
    for (i <- 0 until n) {
      cities(i) = nextInt
    }
    val roads = new Array[(Int, Int)](m)
    for (i <- 0 until m) {
      roads(i) = (nextInt, nextInt)
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("array.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("array.out")))
    solve
    out.close
  }
}