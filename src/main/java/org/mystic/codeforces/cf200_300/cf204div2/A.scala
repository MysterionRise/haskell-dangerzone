package org.mystic.codeforces.cf200_300.cf204div2


import java.io._
import java.util.StringTokenizer

object A {
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
    val a = new Array[Int](n)
    val num = new Array[Int](10)
    for (i <- 0 until n) {
      if (nextInt == 5) {
        num(5) = num(5) + 1
      } else {
        num(0) = num(0) + 1
      }
    }
    if (num(0) > 0) {
      if (num(5) < 9) {
        out.println(0)
      } else {
        val r = num(5) / 9
        for (i <- 0 until r) {
          out.print("555555555")
        }
        for (i <- 0 until num(0)) {
          out.print('0')
        }
      }
    } else {
      out.println(-1)
    }
  }


  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
