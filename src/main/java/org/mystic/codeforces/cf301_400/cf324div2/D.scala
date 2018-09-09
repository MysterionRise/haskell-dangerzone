package org.mystic.codeforces.cf301_400.cf324div2

import java.io._
import java.util.StringTokenizer

object D {

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

  def checkPrime(n: Int): Boolean = {
    if (n == 1)
      return false
    var ind = 2
    var flag = false
    while (!flag && ind <= Math.sqrt(n).toInt) {
      if (n % ind == 0) {
        flag = true
      }
      ind += 1
    }
    !flag
  }

  def solve: Int = {
    val n = nextInt
    var ind = 2
    var flag = false
    if (n == 4) {
      out.println(2)
      out.println("2 2")
      return 0
    }
    while (!flag && ind <= Math.sqrt(n).toInt) {
      if (n % ind == 0) {
        flag = true
      }
      ind += 1
    }
    if (flag) {
      if (checkPrime(n - 3)) {
        out.println(2)
        out.println("3 " + (n - 3))
        return 0
      }
      out.println(3)
      out.print("3 ")
      for (i <- 2 to 1e6.toInt) {
        if (checkPrime(i) && checkPrime(n - i - 3) && n - i - 3 > 0) {
          out.println(i + " " + (n - i - 3))
          return 0
        }
      }
    } else {
      out.println(1)
      out.println(n)
    }
    0
  }
}
