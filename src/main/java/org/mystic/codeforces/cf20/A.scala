package org.mystic.codeforces.cf20

import java.util._
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
    return java.lang.Long.parseLong(next)
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
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
