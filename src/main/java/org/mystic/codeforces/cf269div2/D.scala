package org.mystic.codeforces.cf269div2

import java.util._
import java.io._
import java.util
import scala.annotation.tailrec

object D {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null
  var ans = 0

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

  def prefixFunc(string: Array[Int]): Array[Int] = {
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

  def kmp(t: Array[Int], s: Array[Int]): Unit = {
    val del = new Array[Int](1)
    del(0) = Int.MaxValue
    val p = prefixFunc(s ++ del ++ t)
    for (i <- s.length + 1 to p.length - 1) {
      if (p(i) == s.length) {
        ans += 1
      }
    }
  }

  def solve: Int = {
    val n = nextInt
    val w = nextInt
    val a = new Array[Int](n)
    val b = new Array[Int](w)
    if (w == 1) {
      out.println(n)
      return 1
    }
    val ua = new Array[Int](n - 1)
    val ub = new Array[Int](w - 1)
    for (i <- 0 until n) {
      a(i) = nextInt
    }
    for (j <- 0 until w) {
      b(j) = nextInt
    }
    for (i <- 0 until n - 1) {
      ua(i) = a(i + 1) - a(i)
    }
    for (i <- 0 until w - 1) {
      ub(i) = b(i + 1) - b(i)
    }

    kmp(ua, ub)
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
