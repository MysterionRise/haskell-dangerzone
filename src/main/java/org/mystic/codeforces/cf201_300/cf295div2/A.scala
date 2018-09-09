package org.mystic.codeforces.cf201_300.cf295div2

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
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
