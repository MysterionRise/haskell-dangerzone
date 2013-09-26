package org.mystic.codeforces.gym100229

import java.io._
import java.util.StringTokenizer

object M {
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
    val n = nextInt;
    val arr = new Array[Int](n)
    for (i <- 0 until n) {
      arr(i) = nextInt;
    }
    var i = 0
    var j = n - 1
    while (i <= j) {
      if (i != j) {
        out.print(arr(i) + " ")
        out.print(arr(j) + " ")
      } else {
        out.print(arr(i) + " ")
      }
      i = i + 1;
      j = j - 1;
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("array.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("array.out")))
    solve
    out.close
  }
}