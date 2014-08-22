package org.mystic.codeforces.gym100101

import java.util._
import java.io._

object C {

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
    val n = nextInt
    val a = new Array[Int](n)
    for (i <- 0 until n) {
      a(i) = nextInt
    }
    a.sorted.foreach(x => out.print(x + " "))
    return 1
  }

  def main(args: Array[String]): Unit = {
//    br = new BufferedReader(new InputStreamReader(System.in))
    //    out = new PrintWriter(new BufferedOutputStream(System.out))
            br = new BufferedReader(new InputStreamReader(new FileInputStream("qsort.in")))
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("qsort.out")))
    solve
    out.close
  }
}
