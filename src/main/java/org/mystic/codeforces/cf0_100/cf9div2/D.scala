package org.mystic.codeforces.cf0_100.cf9div2

import java.util._
import java.io._

object D {

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

  def sumOfTrees(dp: Array[Array[Long]], h: Int, n: Int): Long = {
    var temp: Long = 0
    for (i <- 0 to h) {
      temp += dp(i)(n)
    }
    temp
  }

  def solve = {
    val n = nextInt
    val h = nextInt
    val dp = new Array[Array[scala.Long]](36)
    for (i <- 0 to 35) {
      dp(i) = new Array[scala.Long](36)
      Arrays.fill(dp(i), 0)
    }
    dp(0)(0) = 1L
    for (i <- 1 to h) {
      dp(i)(0) = 0L
    }
    for (i <- 1 to n) {
      dp(0)(i) = 0L
    }
    for (i <- 1 to 35) {
      for (j <- 1 to 35) {
        var temp: Long = 0
        for (m <- 1 to j) {
          temp += dp(i - 1)(m - 1) * sumOfTrees(dp, i - 1, j - m) + dp(i - 1)(j - m) * sumOfTrees(dp, i - 2, m - 1)
        }
        dp(i)(j) = temp
      }
    }
    var ans: Long = 0
    for (i <- h to n) {
      ans += dp(i)(n)
    }
    out.println(ans)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
