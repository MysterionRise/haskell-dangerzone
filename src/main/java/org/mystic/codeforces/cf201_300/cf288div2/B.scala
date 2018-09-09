package org.mystic.codeforces.cf201_300.cf288div2

import java.io._
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
    val dollar: Array[Char] = next.toCharArray
    val n = dollar.length
    var ans = false
    var max = ""
    var pos = -1
    var value = '0'
    for (i <- 0 until n) {
      if ((dollar(i) - '0') % 2 == 0 && !ans) {
        val even = dollar(i)
        val end = dollar(n - 1)
        if (end > even) {
          dollar(n - 1) = even
          dollar(i) = end
          max = new String(dollar)
          ans = true
        } else {
          pos = i
          value = even
        }
      }
    }
    if (max == "" && pos != -1) {
      val swap = dollar(n - 1)
      dollar(n - 1) = value
      dollar(pos) = swap
      ans = true
      max = new String(dollar)
    }
    if (ans) {
      out.println(max)
    } else {
      out.println(-1)
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
