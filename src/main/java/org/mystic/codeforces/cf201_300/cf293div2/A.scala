package org.mystic.codeforces.cf201_300.cf293div2

import java.io._
import java.util
import java.util._

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
    val s = next
    val t = next
    val len = s.length
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
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
