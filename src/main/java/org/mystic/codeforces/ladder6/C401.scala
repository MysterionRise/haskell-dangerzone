package org.mystic.codeforces.ladder6

import java.io._
import java.util.StringTokenizer

import scala.StringBuilder
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object C401 {

  var result = true
  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    return st.nextToken
  }

  def nextInt: Int = return Integer.parseInt(next)

  def nextLong: Long = return java.lang.Long.parseLong(next)

  def nextDouble: Double = return java.lang.Double.parseDouble(next)

  def solve: Int = {
    var n = nextInt
    var m = nextInt
    if (n > m + 1)
      out.println(-1)
    else if (n == m + 1) {
      for (i <- 0 until (n + m - 1) / 2) {
        out.print("01")
      }
      out.println("0")
    } else if (m > 2 * n + 2) {
      out.println(-1)
    } else {
      val sb = new StringBuilder()
      while (n != m && n > 0 && m > 0 ) {
        sb.append("110")
        n -= 1
        m -= 2
      }
      while (m > 0 && n > 0) {
        sb.append("10")
        m -= 1
        n -= 1
      }
      var pos = sb.indexOf('1')
      while (n > 0) {
        sb.insert(pos + 1, '0')
        pos = sb.indexOf('1', pos + 2)
        n -= 1
      }
      while (m > 0) {
        sb.append('1')
        m -= 1
      }
      out.println(sb.toString())
    }
    return 0
  }
}
