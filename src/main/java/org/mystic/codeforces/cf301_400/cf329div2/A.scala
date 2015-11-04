package org.mystic.codeforces.cf301_400.cf329div2

import java.io._
import java.util.StringTokenizer

object A {

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
    val n = nextInt
    val words = new Array[String](n)
    for (i <- 0 until n)
      words(i) = next
    var max = 0
    for (c1 <- 'a' to 'z') {
      for (c2 <- 'a' to 'z') {
        if (c1 != c2) {
          var len = 0
          for (i <- 0 until n) {
            var flag = true
            for (j <- 0 until words(i).toCharArray.length) {
              if (words(i).toCharArray().apply(j) != c1 && words(i).toCharArray().apply(j) != c2)
                flag = false
            }
            if (flag) {
              len += words(i).toCharArray.length
            }
          }
          max = Math.max(max, len)
        }
      }
    }
    out.println(max)
    return 0
  }
}
