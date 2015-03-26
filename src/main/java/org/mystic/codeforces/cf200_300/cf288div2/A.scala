package org.mystic.codeforces.cf200_300.cf288div2

import java.io._
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
    val m = nextInt
    val k = nextInt
    val arr = new Array[Array[Boolean]](n + 2)
    for (i <- 0 until n + 2) {
      arr(i) = new Array[Boolean](m + 2)
    }
    var ans = -1
    for (l <- 0 until k) {
      val x = nextInt
      val y = nextInt
      arr(x)(y) = true
      if(
        (ans == -1 && arr(x + 1)(y) && arr(x)(y + 1) && arr(x + 1)(y + 1)) ||
        (ans == -1 && arr(x - 1)(y) && arr(x)(y - 1) && arr(x - 1)(y - 1)) ||
        (ans == -1 && arr(x - 1)(y) && arr(x)(y + 1) && arr(x - 1)(y + 1)) ||
        (ans == -1 && arr(x + 1)(y) && arr(x)(y - 1) && arr(x + 1)(y - 1)))
      {
        ans = l
      }

    }

    if (ans == -1) {
      out.println(0)
    } else {
      out.println(ans + 1)
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