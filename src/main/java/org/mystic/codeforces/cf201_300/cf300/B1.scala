package org.mystic.codeforces.cf201_300.cf300

import java.io._
import java.util
import java.util._

import scala.collection.mutable

object B1 {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

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
    return st.nextToken
  }

  def nextInt: Int = return Integer.parseInt(next)

  def nextLong: Long = return java.lang.Long.parseLong(next)

  def nextDouble: Double = return java.lang.Double.parseDouble(next)

  class MultiHashSet[T <% Comparable[T]] {
    val map = new mutable.HashMap[T, Int]()

    def count(x: T): Int = {
      return map.getOrElse(x, 0)
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
      return true
    }
  }

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

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

  def removeLeadingZero(s: String): String = {
    var ind = 0
    while (ind < s.length && s.charAt(ind) == '0') {
      ind += 1
    }
    s.substring(ind)
  }

  def solve: Int = {
    val ans = new util.ArrayList[String]()
    val z = String.valueOf(nextInt).toCharArray
    val len = z.length
    var flag = false
    while (!flag) {
      flag = true
      var s = ""
      for (i <- 0 until len) {
        if (z(i) != '0') {
          z(i) = (z(i) - 1).toChar
          s += '1'
          flag = false
        } else {
          s += '0'
        }
      }
      if (removeLeadingZero(s).length > 0)
        ans.add(removeLeadingZero(s))
    }
    out.println(ans.size())
    for (i <- 0 until ans.size()) {
      out.print(ans.get(i) + " ")
    }
    return 0
  }
}
