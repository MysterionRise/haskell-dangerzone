package org.mystic.codeforces.cf0_100.cf12div2

import java.util._
import java.lang._
import java.io._

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

  def nextLong: Long = {
    Long.parseLong(next)
  }

  def solve = {
    val keyboard = new Array[Array[Char]](3)
    for (i <- 0 until 3) {
      keyboard(i) = next.toCharArray
    }
    var eq = 0
    for (i <- 0 until 3) {
      for (j <- 0 until 3) {
        if (keyboard(i)(j) == keyboard(3 - i - 1)(3 - j - 1)) {
          eq += 1
        }
      }
    }

    if (eq == 9) {
      out.println("YES")
    } else {
      out.println("NO")
    }

  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
