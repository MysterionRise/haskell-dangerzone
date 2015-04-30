package org.mystic.codeforces.cf201_300.cf262div2

import java.util
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

  def sum(x: Long): Int = {
    val s = x.toString.toCharArray
    var sum = 0
    for (i <- 0 until s.length) {
      sum += s(i) - '0'
    }
    return sum
  }

  def solve: Int = {
    val a = nextInt
    val b = nextInt
    val c = nextInt
    val ans = new util.ArrayList[java.lang.Long]()
    for (i <- 1 until 100) {
      val x = (b.toLong * Math.pow(i, a) + c.toLong).toLong
      if (sum(x) == i && x > 0 && x < 1e9.toInt) {
        ans.add(x)
      }
    }
    Collections.sort(ans)
    out.println(ans.size())
    for (i <- 0 until ans.size()) {
      out.print(ans.get(i) + " ")
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
