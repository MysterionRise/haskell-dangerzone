package org.mystic.codeforces.cf21

import java.util._
import java.io._

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
    val a1 = nextInt
    val b1 = nextInt
    val c1 = nextInt
    val a2 = nextInt
    val b2 = nextInt
    val c2 = nextInt
    if (a1 == 0 && a2 == 0 && b1 == 0 && b2 == 0 && ((c1 == 0 && c2 != 0) || (c2 == 0 && c1 != 0) || (c1 == c2 && c1 != 0) || (c1 != c2 && c1 != 0))) {
      out.println(0)
      return 1
    }
    if (a1 * b2 - a2 * b1 == 0) {
      if (a1 * c2 - a2 * c1 == 0 && b1 * c2 - b2 * c1 == 0) {
        out.println(-1)
      } else {
        out.println(0)
      }
    } else {
      out.println(1)
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