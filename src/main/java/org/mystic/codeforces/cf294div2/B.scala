package org.mystic.codeforces.cf294div2

import java.io._
import java.util.StringTokenizer

import scala.collection.mutable

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

  def nextLong: Long = {
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val a = new mutable.HashMap[Int, Int]()
    for (i <- 0 until n) {
      val int = nextInt
      val x = a.getOrElse(int, 0)
      a.put(int, x + 1)
    }
    val b = new mutable.HashMap[Int, Int]()
    for (i <- 0 until n - 1) {
      val int = nextInt
      val x = b.getOrElse(int, 0)
      b.put(int, x + 1)
    }
    val c = new mutable.HashMap[Int, Int]()
    for (i <- 0 until n - 2) {
      val int = nextInt
      val x = c.getOrElse(int, 0)
      c.put(int, x + 1)
    }
    val list1 = b.toIterator
    while(list1.hasNext) {
      val l1 = list1.next()
      val x = l1._1
      for (j <- 0 until l1._2) {
        val v = a.getOrElse(x, 0)
        a.remove(x)
        if (v - 1 > 0) {
          a.put(x, v - 1)
        }
      }
    }
    val list2 = c.toIterator
    while(list2.hasNext) {
      val l1 = list2.next()
      val x = l1._1
      for (j <- 0 until l1._2) {
        val v = b.getOrElse(x, 0)
        b.remove(x)
        if (v - 1 > 0) {
          b.put(x, v - 1)
        }
      }
    }
    out.println(a.toList.head._1)
    out.println(b.toList.head._1)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}