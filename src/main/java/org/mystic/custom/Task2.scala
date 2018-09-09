package org.mystic.custom

import java.io._
import java.util.StringTokenizer

import scala.collection.mutable

object Task2 {

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

  def solve = {
    println(solution(Array[Int](1)))
  }

  def solution(a: Array[Int]): Int = {
    val distinct = a.toSet.size
    val fromBegin = new Array[Int](a.length)
    val set = new mutable.HashSet[Int]()
    for (i <- a.indices) {
      set.add(a(i))
      fromBegin(i) = set.size
    }
    val fromEnd = new Array[Int](a.length)
    set.clear()
    for (i <- a.length - 1 to 0 by -1) {
      set.add(a(i))
      fromEnd(i) = set.size
    }
    val posBegin = fromBegin.indexOf(distinct)
    val posEnd = fromEnd.length - fromEnd.reverse.indexOf(distinct) - 1

    var pos = posBegin
    var ansFromBegin = 0
    set.clear()
    while (pos >= 0 && set.size != distinct) {
      set.add(a(pos))
      ansFromBegin += 1
      pos -= 1
    }
    pos = posEnd
    var ansFromEnd = 0
    set.clear()
    while (pos < a.length && set.size != distinct) {
      set.add(a(pos))
      ansFromEnd += 1
      pos += 1
    }
    Math.min(ansFromBegin, ansFromEnd)
  }
}
