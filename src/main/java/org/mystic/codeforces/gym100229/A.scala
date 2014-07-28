package org.mystic.codeforces.gym100229

import java.io._
import java.util.StringTokenizer

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

  def solve = {
    out.println(nextInt + nextInt)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("absum.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("absum.out")))
    solve
    out.close
  }
}
