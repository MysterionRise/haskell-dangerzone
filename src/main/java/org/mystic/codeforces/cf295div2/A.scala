package org.mystic.codeforces.cf295div2

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
    val n = nextInt
    val c = next.toCharArray
    val s = new util.HashSet[Character]()
    for (i <- 0 until c.length) {
      s.add(Character.toLowerCase(c(i)))
    }
    if (s.size() == 26) {
      out.println("YES")
    } else {
      out.println("NO")
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
