package org.mystic.codeforces.cf293div2

import java.io._
import java.util
import java.util._

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
    val s = next
    val t = next
    val len = s.size
    val s1 = s.toCharArray
    var i = len - 1
    while (i >= 0 && s(i) == 'z') {
      s1(i) = 'a'
      i -= 1
    }
    s1(i) = (s1(i) + 1).toChar
    val res = new String(s1)
    if (res > s && res < t) {
      out.println(res)
      return 1
    }
    out.println("No such string")
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
