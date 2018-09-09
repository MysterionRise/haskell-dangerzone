package org.mystic.codeforces.cf301_400.cf329div2

import java.io._
import java.util.StringTokenizer

object A {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

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
    st.nextToken
  }

  def nextInt: Int = Integer.parseInt(next)

  def nextLong: Long = java.lang.Long.parseLong(next)

  def nextDouble: Double = java.lang.Double.parseDouble(next)

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
            for (j <- words(i).toCharArray.indices) {
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
    0
  }
}
