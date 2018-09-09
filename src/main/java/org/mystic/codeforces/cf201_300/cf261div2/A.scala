package org.mystic.codeforces.cf201_300.cf261div2

import java.util._
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
    java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val x1 = nextInt
    val y1 = nextInt
    val x2 = nextInt
    val y2 = nextInt
    if (x1 != x2 && y1 != y2) {
      if (Math.abs(x1 - x2) != Math.abs(y1 - y2)) {
        out.println(-1)
      } else {
        out.print(x1 + " " + y2 + " " + x2 + " " + y1)
      }
    } else if (x1 == x2) {
      val d = Math.abs(y1 - y2)
      out.print((x1 + d) + " " + y1 + " " + (x1 + d) + " " + y2)
    } else if (y1 == y2) {
      val d = Math.abs(x1 - x2)
      out.print(x1 + " " + (y1 + d) + " " + x2 + " " + (y1 + d))
    }
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
