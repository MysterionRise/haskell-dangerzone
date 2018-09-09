package org.mystic.codeforces.custom.bayan2015

import java.util._
import java.io._
import java.util

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

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val horDir = next.toCharArray
    val vertDir = next.toCharArray
    val g = new Array[Array[Boolean]](n * m + 1)
    for (i <- 0 to n * m) {
      g(i) = new Array[Boolean](n * m + 1)
    }
    for (i <- 0 until n) {
      for (j <- 1 to m) {
        if (horDir(i) == '>' && j != m) {
          g(i * m + j)(i * m + j + 1) = true
        } else if (horDir(i) == '<' && j != 1) {
          g(i * m + j)(i * m + j - 1) = true
        }
      }
    }
    for (j <- 1 to m) {
      for (i <- 0 until n) {
        if (vertDir(j - 1) == '^' && i != 0) {
          g(i * m + j)(i * m + j - m) = true
        } else if (vertDir(j - 1) == 'v' && i != n - 1) {
          g(i * m + j)(i * m + j + m) = true
        }
      }
    }
    val N = n * m
    for (k <- 1 to N) {
      for (i <- 1 to N) {
        for (j <- 1 to N) {
          g(i)(j) = g(i)(j) || (g(i)(k) && g(k)(j))
        }
      }
    }
    var flag = true
    for (i <- 1 to N) {
      for (j <- 1 to N) {
        if (i != j && !g(i)(j)) {
          flag = false
        }
      }
    }
    if (flag) {
      out.println("YES")
    } else {
      out.println("NO")
    }
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
