package org.mystic.codeforces.cf201_300.cf296div2

import java.io._
import java.util
import java.util._

object B {

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
    java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val s = next.toCharArray
    val t = next.toCharArray
    var curr = 0
    val a = new Array[Array[Int]](27)
    for (i <- 0 until 27) {
      a(i) = new Array[Int](27)
    }
    for (i <- 0 until n) {
      if (s(i) != t(i)) {
        curr += 1
        a(s(i) - 'a')(t(i) - 'a') = i + 1
      }
    }
    for (i <- a.indices) {
      for (j <- i + 1 until a(i).length) {
        if (a(i)(j) >= 1 && a(j)(i) >= 1) {
          out.println(curr - 2)
          out.println(a(i)(j) + " " + a(j)(i))
          return 1
        }
      }
    }

    for (i <- a.indices) {
      var a1 = 0
      var a2 = 0
      for (j <- 0 until a(i).length) {
        a1 = Math.max(a1, a(i)(j))
        a2 = Math.max(a2, a(j)(i))
      }
      if (a1 >= 1 && a2 >= 1) {
        out.println(curr - 1)
        out.println(a1 + " " + a2)
        return 1
      }
    }
    out.println(curr)
    out.println("-1 -1")
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
