package org.mystic.codeforces.cf301_400.cf303div2

import java.io._
import java.util._

import scala.collection.mutable

object B {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = Integer.parseInt(next)

  def nextLong: Long = java.lang.Long.parseLong(next)

  def nextDouble: Double = java.lang.Double.parseDouble(next)

  class MultiHashSet[T <% Comparable[T]] {
    val map = new mutable.HashMap[T, Int]()

    def count(x: T): Int = {
      map.getOrElse(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      true
    }
  }

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

    def count(x: T): Int = {
      map.getOrDefault(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def first(): T = map.firstKey()

    def last(): T = map.lastKey()

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      true
    }
  }

  def solve: Int = {
    val s = next.toCharArray
    val p = next.toCharArray
    val n = s.length
    var diff = 0
    for (i <- 0 until n) {
      if (s(i) != p(i))
        diff += 1
    }
    if (diff % 2 == 1) {
      out.println("impossible")
    } else {
      var first = 0
      var second = 0
      for (i <- 0 until n) {
        if (s(i) != p(i)) {
            if (first > second) {
              out.print(p(i))
              second += 1
            } else {
              out.print(s(i))
              first += 1
            }
        } else {
          out.print(s(i))
        }
      }
    }
    0
  }
}
