package org.mystic.codeforces.cf296div2

import java.io._
import java.util
import java.util._

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

  class Interval(val start: Int, val length: Int) extends Comparable[Interval] {
    override def compareTo(o: Interval): Int = Integer.compare(this.length, o.length)
  }

  def solve: Int = {
    val w = nextInt
    val h = nextInt
    val n = nextInt
    val m1 = new util.HashMap[Int, Interval]()
    val i1 = new util.TreeSet[Interval]()
    val interval = new Interval(0, w)
    i1.add(interval)
    
    for (i <- 0 until n) {
      val dir = next.charAt(0)
      var z = nextInt
      if (dir == 'H') {

      } else {

      }

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
