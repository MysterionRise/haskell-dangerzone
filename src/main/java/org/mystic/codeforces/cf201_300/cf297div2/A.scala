package org.mystic.codeforces.cf201_300.cf297div2

import java.io._
import java.util
import java.util.StringTokenizer
import java.util.TreeMap

import scala.collection.mutable

object A {

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
    val map = next.toCharArray
    val size = 2 * n - 2
    val keys = new MultiHashSet[Char]()
    var ans = 0
    for (i <- 0 until size by 2) {
      keys.add(map(i).toUpper)
      if (keys.count(map(i + 1)) > 0) {
        keys.remove(map(i + 1))
      } else {
        ans += 1
      }
    }
    out.println(ans)
    0
  }
}
