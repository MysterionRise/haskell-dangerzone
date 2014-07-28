package org.mystic.codeforces.gym100229

import java.io._
import java.util.StringTokenizer

object K {
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

  def solve = {
    val n = nextInt
    val arr = new Array[Int](n + 2)
    val dp = new Array[Int](n + 2)
    arr(0) = 0
    dp(0) = 0
    arr(n + 1) = 0
    for (i <- 1 to n) {
      arr(i) = nextInt
    }
    val k = nextInt
    for (i <- 1 to n + 1) {
      var max = Int.MinValue
      for (j <- Math.max(0, i - k) to i - 1) {
        max = Math.max(max, dp(j))
      }
      dp(i) = arr(i) + max
    }
    out.println(dp(n + 1))
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("ladder.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("ladder.out")))
    solve
    out.close
  }
}