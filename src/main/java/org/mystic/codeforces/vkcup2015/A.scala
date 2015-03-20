package org.mystic.codeforces.vkcup2015

import java.io._
import java.util.StringTokenizer

import scala.collection.mutable

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
    val n = nextInt
    val g = new Array[Array[Boolean]](500)
    val map = new mutable.HashMap[String, Int]()
    for (i <- 0 until n) {
      val name1 = next.toLowerCase
      next
      val name2 = next.toLowerCase
      val a = map.getOrElse(name2, 0)
      val b = map.getOrElse(name1, 0)
      map.put(name1, Math.max(a + 1, b))
    }
    var max = 0
    map.keySet.foreach(key => {
      max = Math.max(max, map.get(key).get)
    })
    out.println(max + 1)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
