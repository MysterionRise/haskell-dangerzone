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

  def ans(s: String, t: String, ind: Int): String = {
    var res = ""
    for (i <- 0 until s.length) {
      if (i != ind) {
        res += s.charAt(i)
      } else {
        res += (t.charAt(i) - 1).toChar
      }
    }
    return res
  }

  def solve: Int = {
    val s = next
    val t = next
    val len = s.size
    val a = new Array[Int](len)
    for (i <- 0 until len) {
      a(i) = t.charAt(i) - s.charAt(i)
    }
    var res = ""
    var ind1 = -1
    var ind2 = -1
    for (i <- 0 until len) {
      if (a(i) >= 2) {
        out.println(ans(s, t, i))
        return 1
      }
      if (a(i) == 1 && ind1 == -1) {
        ind1 = i
      }
    }
    for (i <- 0 until len) {
      if (i < ind1) {
        res += s.charAt(i)
      } else
      if (i == ind1) {
        res += t.charAt(i)
      } else {
        res += "a"
      }
    }
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
