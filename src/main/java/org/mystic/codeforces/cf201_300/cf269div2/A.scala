package org.mystic.codeforces.cf201_300.cf269div2

import java.util._
import java.io._
import java.util

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
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val a = new Array[Int](6)
    val m = new Array[Int](10)
    for (i <- 0 until 6) {
      a(i) = nextInt
      m(a(i)) += 1
    }
    var hands = false
    var e = false
    for (i <- 0 until m.length) {
      if (m(i) >= 4) {
        hands = true
        m(i) -= 4
        for (j <- 0 until m.length) {
          if (m(j) == 2) {
            e = true
          }
        }
        m(i) += 4
      }
    }
    if (!hands) {
      out.println("Alien")
    } else {
      if (e) {
        out.println("Elephant")
      } else {
        out.println("Bear")
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
