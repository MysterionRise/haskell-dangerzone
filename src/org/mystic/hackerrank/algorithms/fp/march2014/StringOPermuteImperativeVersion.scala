package org.mystic.hackerrank.algorithms.fp

import java.io.{BufferedOutputStream, InputStreamReader, BufferedReader, PrintWriter}
import java.util.StringTokenizer

/**
 * This version gets 20, thanks to imperative approach
 */
object StringOPermuteImperativeVersion {

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

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def swap(x: String): Unit = {
    val sz = x.size
    var i = 0
    while (i < sz) {
      print(x(i + 1))
      print(x(i))
      i = i + 2
    }
    println
  }

  def solve {
    val sz = nextInt
    var i = 0
    while (i < sz) {
      swap(next)
      i = i + 1
    }
  }
}
