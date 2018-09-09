package org.mystic.ir.bayan.qual2014

import java.io._
import java.util._

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
    val vowels = Array('a', 'e', 'i', 'o', 'u')
    (0 until nextInt).foreach(_ => {
      next.filter(c => vowels.contains(c)).length match {
        case n if n % 2 == 0 => out.println("DOKHTAR")
        case _ => out.println("PESAR")
      }
    })
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
