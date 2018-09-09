package org.mystic.codeforces.cf201_300.cf297div2

import java.io._
import java.util
import java.util.StringTokenizer
import java.util.TreeMap

import scala.StringBuilder
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
    val m = nextInt
    val length: Int = s.length
    val cnt = new Array[Int](length)
    val bitSet = new util.BitSet()
    val bits = new Array[Boolean](length)
    for (i <- 0 until length) {
      bits(i) = true
    }
    for (i <- 0 until m) {
      val x = nextInt - 1
      bits(x) = !bits(x)
      bits(length - x - 1) = !bits(length - x - 1)
    }
    var curr = 0
    val sb = new StringBuilder()
    for (i <- 0 until length / 2) {
      if (!bits(i)) {
        curr += 1
      }
      if (curr % 2 == 0) {
        out.print(s(i))
        sb.append(s(length - i - 1))
      } else {
        out.print(s(length - i - 1))
        sb.append(s(i))
      }
    }
    if (length % 2 == 1) {
      out.print(s(length / 2))
    }
    out.println(sb.reverse.toString())
    0
  }
}
