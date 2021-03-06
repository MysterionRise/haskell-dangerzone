package org.mystic.codeforces.cf201_300.cf259div2

import java.util._
import java.io._

object B {

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

  def nextLong: Long = {
    java.lang.Long.parseLong(next)
  }

  def smartSolver(n: Int, a: Array[Int]): Int = {
    var ind = 1
    var flag = false
    var prev = a(0)
    var count = 0
    var terminate = 0
    while (terminate <= 3 * n && !flag) {
      if (prev <= a(ind)) {
        count += 1
      } else {
        count = 0
      }
      if (count == n - 1) {
        flag = true
      }
      else {
        prev = a(ind)
        ind = (ind + 1) % n
        terminate += 1
      }
    }
    if (flag) {
      n - ind - 1
    } else {
      -1
    }
  }

  def solve: Int = {
    val n = nextInt
    val a = new Array[Int](n)
    for (i <- 0 until n) {
      a(i) = nextInt
    }
    out.println(smartSolver(n, a))
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}