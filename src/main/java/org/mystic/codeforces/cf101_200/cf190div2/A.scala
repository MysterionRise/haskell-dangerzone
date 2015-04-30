package org.mystic.codeforces.cf101_200.cf190div2

import java.util._
import java.lang._
import java.io._

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

  def nextLong: Long = {
    return Long.parseLong(next)
  }

  def solve = {
    val n = nextInt
    val m = nextInt
    val k = math.max(n, m) + math.min(n, m) - 1
    out.println(k)
    for (i <- 1 to math.max(n, m)) {
      if (n > m) {
        out.println(i + " 1")
      } else {
        out.println("1 " + i)
      }
    }
    for (i <- 2 to math.min(n, m)) {
      if (n > m) {
        out.println("1 " + i)
      } else {
        out.println(i + " 1")
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
