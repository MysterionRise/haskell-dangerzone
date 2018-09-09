package org.mystic.codeforces.cf301_400.cf303div2

import java.io._
import java.util._

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
    val a = new Array[Array[Int]](n)
    val machines = new mutable.HashSet[Int]()
    for (i <- 0 until n) {
      a(i) = new Array[Int](n)
      machines.add(i)
    }
    for (i <- 0 until n) {
      for (j <- 0 until n) {
        a(i)(j) = nextInt
        a(i)(j) match {
          case 1 => machines.remove(i)
          case 2 => machines.remove(j)
          case 3 => {
            machines.remove(i)
            machines.remove(j)
          }
          case _ =>
        }
      }
    }
    out.println(machines.size)
    val list = new Array[Int](machines.size)
    var ind = 0
    for (x <- machines) {
      list(ind) = x + 1
      ind += 1
    }
    list.sorted.foreach(x => out.print(x + " "))

    0
  }
}
