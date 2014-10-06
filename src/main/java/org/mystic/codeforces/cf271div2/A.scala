package org.mystic.codeforces.cf271div2

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
    val dir = next
    val line = next.toCharArray
    val key = new Array[Array[Char]](3)
    key(0) = "qwertyuiop".toCharArray
    key(1) = "asdfghjkl;".toCharArray
    key(2) = "zxcvbnm,./".toCharArray
    val b = new StringBuilder
    if (dir == "R") {
      for (i <- 0 until line.length) {
        for (j <- 0 until 3) {
          for (k <- 0 until key(j).length) {
            if (key(j)(k) == line(i)) {
              b.append(key(j)(k - 1))
            }
          }
        }
      }
    } else {
      for (i <- 0 until line.length) {
        for (j <- 0 until 3) {
          for (k <- 0 until key(j).length) {
            if (key(j)(k) == line(i)) {
              b.append(key(j)(k + 1))
            }
          }
        }
      }
    }
    out.println(b.toString())
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
