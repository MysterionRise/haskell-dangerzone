package org.mystic.codeforces.cf0_100.cf18div2

import java.util._
import java.lang._
import java.io._

object A {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = {
    Integer.parseInt(next)
  }

  def nextLong: Long = {
    Long.parseLong(next)
  }

  def sqDist(x1: Int, y1: Int, x2: Int, y2: Int): Int = {
    (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)
  }

  def dist(x1: Int, y1: Int, x2: Int, y2: Int): Double = {
    Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
  }

  def isSquareTriangle(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Boolean = {
    val ab2 = sqDist(x1, y1, x2, y2)
    val bc2 = sqDist(x2, y2, x3, y3)
    val ac2 = sqDist(x1, y1, x3, y3)
    val ab = dist(x1, y1, x2, y2)
    val bc = dist(x3, y3, x2, y2)
    val ac = dist(x1, y1, x3, y3)
    if (ac < ab + bc && ab < ac + bc && bc < ac + ab) {
      if (ab2 + bc2 == ac2 || ab2 + ac2 == bc2 || bc2 + ac2 == ab2) {
        return true
      }
    }
    false
  }

  def solve: Int = {
    val x1 = nextInt
    val y1 = nextInt
    val x2 = nextInt
    val y2 = nextInt
    val x3 = nextInt
    val y3 = nextInt
    if (isSquareTriangle(x1, y1, x2, y2, x3, y3)) {
      out.println("RIGHT")
      return 1
    }
    if (isSquareTriangle(x1 + 1, y1, x2, y2, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1 - 1, y1, x2, y2, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1 + 1, x2, y2, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1 - 1, x2, y2, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2 + 1, y2, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2 - 1, y2, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2, y2 + 1, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2, y2 - 1, x3, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2, y2, x3 + 1, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2, y2, x3 - 1, y3)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2, y2, x3, y3 + 1)) {
      out.println("ALMOST")
      return 1
    }
    if (isSquareTriangle(x1, y1, x2, y2, x3, y3 - 1)) {
      out.println("ALMOST")
      return 1
    }
    out.println("NEITHER")
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
