package org.mystic.codeforces.ladder6

import java.io._
import java.util.StringTokenizer

object C489 {

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

  def findMin(sum: Int, length: Int): Unit = {
    val a = new Array[Int](length)
    for (d <- 1 to 9) {
      a(0) = d
      var left = sum - d
      for (pos <- length - 1 to 1 by -1) {
        if (left >= 0) {
          val digit = Math.min(9, left)
          a(pos) = digit
          left -= digit
        }
      }
      if (left == 0) {
        out.print(s"${a.mkString} ")
        return
      }
    }
    result = false
  }

  def findMax(sum: Int, length: Int): Unit = {
    val a = new Array[Int](length)
    for (d <- 9 to 1 by -1) {
      a(0) = d
      var left = sum - d
      for (pos <- 1 to length - 1) {
        if (left >= 0) {
          val digit = Math.min(9, left)
          a(pos) = digit
          left -= digit
        }
      }
      if (left == 0) {
        out.print(s"${a.mkString}")
        return
      }
    }
    result = false
  }


  def solve: Int = {
    val m = nextInt
    val s = nextInt
    var min = Int.MaxValue
    val max = Int.MinValue
    if (m == 1 && s == 0) {
      out.println("0 0")
      return 0
    }
    if (!(s >= 0 && s <= 9 * m)) {
      out.println("-1 -1")
      return 0
    }
    findMin(s, m)
    findMax(s, m)
    if (!result) {
      out.println("-1 -1")
    }
    return 0
  }
}
