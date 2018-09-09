package org.mystic.codeforces.gyms.gym100229

import java.io._
import java.util.StringTokenizer

object M {
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

  def solve = {
    val n = nextInt
    val arr = new Array[Int](n)
    for (i <- 0 until n) {
      arr(i) = nextInt
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
      i = i + 1
      j = j - 1
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("array.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("array.out")))
    solve
    out.close
  }
}