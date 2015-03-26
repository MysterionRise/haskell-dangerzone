package org.mystic.codeforces.cf200_300.cf294div2

import java.io._
import java.util._

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
    var white = 0
    var black = 0
    for (i <- 0 until 8) {
      val ch = next.toCharArray
      for (j <- 0 until 8) {
        ch(j) match {
          case 'Q' =>white += 9
          case 'R'=>white += 5
          case 'B'=>white +=3
          case 'N'=>white +=3
          case 'P'=>white +=1
          case 'q'=>black += 9
          case 'r'=>black += 5
          case 'b'=>black += 3
          case 'n'=>black +=3
          case 'p'=>black +=1
          case _ =>
        }
      }
    }
    if (white > black) {
      out.println("White")
    } else if (black > white) {
      out.println("Black")
    } else {
      out.println("Draw")
    }
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
