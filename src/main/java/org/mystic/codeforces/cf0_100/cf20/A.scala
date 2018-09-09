package org.mystic.codeforces.cf0_100.cf20

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
    val path = next
    var i = 1
    var ans = ""
    while (i < path.length) {
      if (path(i) == '/' && path(i - 1) == '/') {
      } else {
        ans += path(i)
      }
      i += 1
    }
    i = ans.length - 1
    while (i >= 0 && ans(i) == '/') {
      i -= 1
    }
    out.println("/" + ans.substring(0, i + 1))
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
