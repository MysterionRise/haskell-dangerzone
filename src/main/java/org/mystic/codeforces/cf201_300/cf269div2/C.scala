package org.mystic.codeforces.cf201_300.cf269div2

import java.util._
import java.io._
import java.util
import scala.annotation.tailrec

object C {

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

  def baseHouse(n: Long): Long = {
    return (3L * n * n + n) / 2L
  }

  def solve: Int = {
    val n = nextLong
    var ans = 0
    for (i <- 1 to 1e6.toInt) {
      if (n - baseHouse(i) >= 0 && (n - baseHouse(i)) % 3 == 0) {
        ans += 1
      }
    }
    out.println(ans)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
