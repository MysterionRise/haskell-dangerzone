package org.mystic.codeforces.gyms.gym100229

import java.io._
import java.util.StringTokenizer

object O {
  val out: PrintWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream("road.out")))
  var br: BufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("road.in")))
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
    val length = next.zip(1 to n).filter(_._1 == 'W').map(_._2).intersect(next.zip(1 to n).filter(_._1 == 'W').map(_._2)).length
    length match {
      case 0 => out.println("YES")
      case _ => out.println("NO")
    }
  }

  def main(args: Array[String]): Unit = {
    solve
    out.close
  }
}
