package org.mystic.codeforces.bayan2015

import java.util._
import java.io._
import java.util

object A {

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
    var k = nextInt
    out.println("+------------------------+")
    val ans = new Array[util.ArrayList[Char]](4)
    for (i <- 0 until 4) {
      ans(i) = new util.ArrayList[Char]()
      ans(i).add('|')
    }
    for (j <- 0 until 11) {
      for (i <- 0 until 4) {
        if (k > 0) {
          if (i == 2) {
            if (j == 0) {
              ans(i).add('O')
              ans(i).add('.')
              k -= 1
            } else {
              ans(i).add('.')
              ans(i).add('.')
            }
          }
          else {
            ans(i).add('O')
            ans(i).add('.')
            k -= 1
          }
        }
        else {
          if (i == 2 && j > 0) {
            ans(i).add('.')
            ans(i).add('.')
          } else {
            ans(i).add('#')
            ans(i).add('.')
          }
        }
      }
    }
    ans(0).add('|')
    ans(0).add('D')
    ans(0).add('|')
    ans(0).add(')')
    ans(1).add('|')
    ans(1).add('.')
    ans(1).add('|')
    ans(2).add('.')
    ans(2).add('.')
    ans(2).add('|')
    ans(3).add('|')
    ans(3).add('.')
    ans(3).add('|')
    ans(3).add(')')
    for (i <- 0 until 4) {
      for (j <- 0 until ans(i).size()) {
        out.print(ans(i).get(j))
      }
      out.println
    }
    out.println("+------------------------+")
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
