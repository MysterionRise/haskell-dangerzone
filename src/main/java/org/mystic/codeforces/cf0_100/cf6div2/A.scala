package org.mystic.codeforces.cf0_100.cf6div2

import java.util._
import java.lang._
import java.io._

object A {

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
    Long.parseLong(next)
  }

  def solve = {
    val len = new Array[Int](4)
    for (i <- 0 until 4) {
      len(i) = nextInt
    }
    var flag = false
    for (i <- 0 until 4) {
      for (j <- 0 until 4) {
        for (k <- 0 until 4) {
          if (i != j && j != k && i != k) {
            if (len(i) < len(j) + len(k) &&
              len(j) < len(i) + len(k) &&
              len(k) < len(j) + len(i) && !flag) {
              out.println("TRIANGLE")
              flag = true
            }
          }
        }
      }
    }
    if (!flag) {
      for (i <- 0 until 4) {
        for (j <- 0 until 4) {
          for (k <- 0 until 4) {
            if (i != j && j != k && i != k) {
              if ((len(i) <= len(j) + len(k) &&
                len(j) <= len(i) + len(k) &&
                len(k) <= len(j) + len(i)) && !flag) {
                out.println("SEGMENT")
                flag = true
              }
            }
          }
        }
      }
    }
    if (!flag) {
      out.println("IMPOSSIBLE")
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
