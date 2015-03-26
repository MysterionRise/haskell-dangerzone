package org.mystic.codeforces.cf0_100.cf9div2

import java.util._
import java.lang._
import java.io._

object B {

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
    return Long.parseLong(next)
  }

  def solve = {
    val n = nextInt
    val busSpeed = nextInt
    val manSpeed = nextInt
    val stations: Array[Int] = new Array[Int](n - 1)
    nextInt
    for (i <- 0 until n - 1) {
      stations(i) = nextInt
    }
    val x = nextInt
    val y = nextInt
    val processedStations = stations.map(X => solver(X, busSpeed, manSpeed, x, y)).zip(Stream from 2).sortWith(customComparator)
    out.println(processedStations(0)._2)
  }

  def customComparator(first: ((Double, Double), Int), second: ((Double, Double), Int)) =
    if (first._1._1 == second._1._1) first._1._2 < second._1._2 else first._1._1 < second._1._1

  def solver(station: Int, busSpeed: Int, manSpeed: Int, x: Int, y: Int): (Double, Double) = {
    (Math.sqrt(dist(station, 0, x, y)) * busSpeed + Math.abs(station) * manSpeed, dist(station, 0, x, y))
  }

  def dist(x: Double, y: Double, x1: Double, y1: Double): Double = {
    (x - x1) * (x - x1) + (y - y1) * (y - y1)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
