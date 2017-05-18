package org.mystic

import java.io._
import java.util.StringTokenizer
import java.util.TreeMap

import org.mystic.DotaBuffCall._

import scala.collection.mutable
import scala.io.StdIn

object Task1 {

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

  def solve = {
    println(solution(Array[Int](1, 1, 1, 1, 1), Array[Int](2, 2, 2, 2, 2), 5, 5, 1))
  }

  def solution(a: Array[Int], b: Array[Int], m: Int, x: Int, y: Int): Int = {

    var ans = 0
    var zipped = a.zip(b)
    while (!zipped.isEmpty) {
      var curWeight = 0L
      var curLimit = 0L
      val passengers = zipped.takeWhile(el => {
        curWeight += el._1
        curLimit += 1L
        curWeight <= y && curLimit <= x
      })
      zipped = zipped.drop(passengers.length)
      val sortedByFloor = passengers.sortBy(f => f._2).map(pass => pass._2).toSet
      ans += sortedByFloor.size + 1
    }

    ans
  }
}
