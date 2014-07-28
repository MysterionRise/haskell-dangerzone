package org.mystic.hackerrank.algorithms.fp.march2014

import java.io.{BufferedOutputStream, InputStreamReader, BufferedReader, PrintWriter}
import java.util.StringTokenizer


object SubstringSearching {

  def prefixFunc(string: String): Array[Int] = {
    val π = new Array[Int](string.length)
    π(0) = 0
    for (i <- 1 to string.length - 1) {
      var j = π(i - 1)
      while (j != 0 && string(j) != string(i)) {
        j = π(j - 1)
      }
      if (string(j) == string(i)) {
        π(i) = j + 1
      } else {
        π(i) = 0
      }
    }
    π
  }

  def kmp(t: String, s: String): Unit = {
    val p = prefixFunc(s + "#" + t)
    for (i <- s.length + 1 to p.length - 1) {
      if (p(i) == s.length) {
        println("YES")
        return
      }
    }
    println("NO")
  }

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

  def solve {
    val testcases = nextInt
    var i = 0
    while (i < testcases) {
      i = i + 1
      kmp(next, next)
    }
  }
}
