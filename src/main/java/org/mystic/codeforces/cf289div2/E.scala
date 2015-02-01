package org.mystic.codeforces.cf289div2

import java.io._
import java.util.{Locale, StringTokenizer}

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
    var ans = 0.0f
    (1 to n).foreach(i => {
      if (vowels.contains(chars(i - 1))) {
        val min = Math.min(i, n - i + 1)
        var k = 1
        for (j <- 1 to min) {
          ans += (1.0f * j) / k
          k += 1
        }
        for (j <- min + 1 to n) {
          ans += 
        }
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
