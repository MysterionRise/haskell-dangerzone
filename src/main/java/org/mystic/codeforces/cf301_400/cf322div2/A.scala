package org.mystic.codeforces.cf301_400.cf322div2

import java.io._
import java.util.{StringTokenizer, TreeMap}

import scala.collection.mutable

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
    val a = nextInt
    val b = nextInt
    val c = Math.min(a, b)
    val d = (Math.max(a, b) - c) / 2
    out.println(c + " " + d)
    return 0
  }
}
