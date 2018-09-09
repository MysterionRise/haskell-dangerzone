package org.mystic.codeforces.gyms.gym100229

import java.io._
import java.util.StringTokenizer

object A {
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
