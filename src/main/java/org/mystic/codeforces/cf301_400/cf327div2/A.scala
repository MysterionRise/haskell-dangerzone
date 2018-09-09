package org.mystic.codeforces.cf301_400.cf327div2

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
    val l = nextInt
    val p = nextInt
    val q = nextInt
    val t = (1.0d * l) / (1.0d * p + q * 1.0d)
    val first = 1.0d * t * p
    println(first)
    0
  }
}
