package org.mystic.codeforces.cf200_300.cf296div2

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

  class MultiTreeSet[T <: Comparable[T]] {
    val map = new util.TreeMap[T, Int]()

    def count(x: T): Int = {
      val res = map.get(x)
      if (res == null)
        return 0
      return res
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def first(): T = return map.firstKey()

    def last(): T = return map.lastKey()

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      return true
    }
  }

  def solve: Int = {
    val w = nextInt
    val h = nextInt
    val n = nextInt
    val hor = new TreeSet[Int]()
    val vert = new TreeSet[Int]()
    val hCuts = new MultiTreeSet[Integer]
    val vCuts = new MultiTreeSet[Integer]
    hCuts.add(h)
    vCuts.add(w)
    hor.add(0)
    hor.add(h)
    vert.add(0)
    vert.add(w)
    for (i <- 0 until n) {
      val dir = next.toCharArray.apply(0)
      if (dir == 'H') {
        val pos = h - nextInt
        val lo = hor.lower(pos)
        val hi = hor.higher(pos)
        hCuts.remove(hi - lo)
        hCuts.add(hi - pos)
        hCuts.add(pos - lo)
        hor.add(pos)
      } else {
        val pos = nextInt
        val lo = vert.lower(pos)
        val hi = vert.higher(pos)
        vCuts.remove(hi - lo)
        vCuts.add(hi - pos)
        vCuts.add(pos - lo)
        vert.add(pos)
      }
      out.println(vCuts.last().toLong * hCuts.last().toLong)
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
