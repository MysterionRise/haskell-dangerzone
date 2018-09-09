package org.mystic.codeforces.cf301_400.cf329div2

import java.io._
import java.util.{StringTokenizer, TreeMap}

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


  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = Integer.parseInt(next)

  def nextLong: Long = java.lang.Long.parseLong(next)

  def nextDouble: Double = java.lang.Double.parseDouble(next)

  def solve: Int = {
    val n = nextInt
    val x1 = nextLong
    val x2 = nextLong
    val arr = new Array[(Long, Long)](n)
    for (i <- 0 until n) {
      arr(i) = (nextLong, nextLong)
    }
    val left = new Array[(Long, Long)](n)
    val right = new MultiTreeSet[Long]
    for (i <- 0 until n) {
      left(i) = (arr(i)._1 * x1 + arr(i)._2, arr(i)._1 * x2 + arr(i)._2)
      right.add(arr(i)._1 * x2 + arr(i)._2)
    }
    val sorted = left.sorted
    var i = 0
    while (i < n) {
      var j = i
      var y1 = sorted(i)._2
      while (j < n && sorted(j)._1 == sorted(i)._1) {
        y1 = Math.max(y1, sorted(j)._2)
        right.remove(sorted(j)._2)
        j += 1
      }
      if (right.map.isEmpty) {
        out.print("NO")
        return 0
      }
      val min = right.map.firstKey()
      val max = right.map.lastKey()
      if (y1 > min) {
        out.print("YES")
        return 0
      }
      i = j
    }
    out.print("NO")
    0
  }


}
