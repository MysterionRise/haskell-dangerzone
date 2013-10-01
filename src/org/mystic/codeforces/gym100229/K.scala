package org.mystic.codeforces.gym100229

import java.io._
import java.util.StringTokenizer

object K {
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

  def solve = {
    val n = nextInt
    val arr = new Array[Int](n + 2)
    arr(0) = 0
    arr(n + 1) = 0
    for (i <- 1 to n) {
      arr(i) = nextInt
    }
    val k = nextInt
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    //    br = new BufferedReader(new InputStreamReader(new FileInputStream("ladder.in")))
    //    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("ladder.out")))
    solve
    out.close
  }
}