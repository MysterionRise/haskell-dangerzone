package org.mystic.codeforces.cf202div2

import java.io._
import java.util.StringTokenizer

object B {
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
    val v = nextInt
    val num: Array[Int] = new Array[Int](9)
    for (i <- 0 until 9) {
      num(i) = nextInt
    }
    var min: Int = Integer.MAX_VALUE
    var pos: Int = -1
    for (i <- 0 until 9) {
      if (num(i) < min) {
        min = num(i)
        pos = i
      }
    }
    val numberOfDigits: Int = v / min
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("absum.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("absum.out")))
    solve
    out.close
  }
}
