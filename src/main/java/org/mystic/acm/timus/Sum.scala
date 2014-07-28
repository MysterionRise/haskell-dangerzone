package org.mystic.acm.timus

import java.lang.String
import java.io.{BufferedOutputStream, PrintWriter, InputStreamReader, BufferedReader}
import java.util.StringTokenizer

object Sum {

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

  def solve: Unit = {
    out.println(nextInt + nextInt)
  }


  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
