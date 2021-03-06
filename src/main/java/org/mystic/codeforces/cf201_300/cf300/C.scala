package org.mystic.codeforces.cf201_300.cf300

import java.io._
import java.util
import java.util._

import scala.collection.mutable

object C {

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
    val n = nextInt
    val m = nextInt
    val records = new Array[(Int, Int)](m)
    for (i <- 0 until m ){
      records(i) = (nextInt, nextInt)
    }
    var max = records(0)._2 + records(0)._1 - 1
    for (i <- 1 until m) {
      val diff = records(i)._1 - records(i - 1)._1
      val hDiff = records(i)._2 - records(i - 1)._2
      max = Math.max(records(i)._2, max)
      if (Math.abs(hDiff) > diff) {
        out.println("IMPOSSIBLE")
        return 1
      }
      val x = (diff - hDiff) / 2
      max = Math.max(records(i)._2 + x, max)
    }
    max = Math.max(records(m - 1)._2 + n - records(m - 1)._1, max)
    out.println(max)
    0
  }
}
