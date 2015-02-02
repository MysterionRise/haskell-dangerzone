package org.mystic.codeforces.cf289div2

import java.io._
import java.util.StringTokenizer

object E {

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
    val chars = next.toCharArray
    val n = chars.length
    val vowels = Set('A', 'Y', 'E', 'U', 'I', 'O')
    var ans = 0.0d
    val sum1 = new Array[Double](n + 1)
    for (i <- 1 until n + 1) {
      sum1(i) = sum1(i - 1) + (1.0d / i)
    }
    val sum2 = new Array[Double](n + 1)
    sum2(n) = 1.0d / n
    for (i <- n - 1 to 0 by - 1) {
      sum2(i) = sum2(i + 1) + (1.0d * n - i + 1) / i
    }
    (1 to n).foreach(i => {
      if (vowels.contains(chars(i - 1))) {
        val min = Math.min(i, n - i + 1)
        val max = Math.max(i, n - i + 1)
        ans += min - 1
        ans += min * (sum1(max - 1) - sum1(min - 1))
        ans += sum2(max)
      }
    })
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
