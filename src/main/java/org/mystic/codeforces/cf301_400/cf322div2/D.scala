package org.mystic.codeforces.cf301_400.cf322div2

import java.io._
import java.util._

import scala.StringBuilder
import scala.collection.mutable

object D {

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

  case class Rectangle(width: Int, height: Int) {

    def normalize(): Rectangle = {
      new Rectangle(Math.min(width, height), Math.max(width, height))
    }

    def maximize(): Rectangle = {
      new Rectangle(Math.max(width, height), Math.min(width, height))
    }

    def rotate(): Rectangle = {
      new Rectangle(height, width)
    }
  }

  def getSecond(i: Int): Int = {
    i match {
      case 0 => 1
      case 1 => 2
      case 2 => 0
    }
  }

  def getThird(i: Int): Int = {
    i match {
      case 0 => 2
      case 1 => 0
      case 2 => 1
    }
  }

  def rectLetter(i: Int): String = {
    i match {
      case 0 => "A"
      case 1 => "B"
      case 2 => "C"
    }
  }

  def appendZeroes(s: String): String = {
    val sb = new StringBuilder()
    while (sb.length < 3 - s.length) {
      sb.append("0")
    }
    sb.append(s).toString()
  }

  def rotate(rectangle: Rectangle, s: String, i: Int) = if (s.charAt(i) == '1') rectangle else rectangle.rotate()

  def checkRects(i: Int, rects: Array[Rectangle]): Boolean = {
    for (i <- 0 until 3) {
      for (k <- 0 to 8) {
        val s = appendZeroes(Integer.toBinaryString(k))
        val fst = rotate(rects(i), s, 0)
        val snd = rotate(rects(getSecond(i)), s, 1)
        val thrd = rotate(rects(getThird(i)), s, 2)
        if (fst.height == snd.width + thrd.width && fst.height == fst.width + snd.height && snd.height == thrd.height) {
          // output the result
          out.println(fst.height)
          for (j <- 0 until fst.height) {
            for (k <- 0 until fst.height) {
              if (j < snd.height) {
                if (k < snd.width) {
                  out.print(rectLetter(getSecond(i)))
                } else {
                  out.print(rectLetter(getThird(i)))
                }
              } else {
                out.print(rectLetter(i))
              }
            }
            out.println()
          }
          return true
        }
      }
    }
    false
  }

  def solve: Int = {
    val x1 = nextInt
    val y1 = nextInt
    val a = new Rectangle(x1, y1).normalize
    val a1 = new Rectangle(x1, y1).maximize
    val x2 = nextInt
    val y2 = nextInt
    val b = new Rectangle(x2, y2).normalize
    val b1 = new Rectangle(x2, y2).maximize
    val x3 = nextInt
    val y3 = nextInt
    val c = new Rectangle(x3, y3).normalize
    val c1 = new Rectangle(x3, y3).maximize
    if (a.height == b.height && b.height == c.height && a.width + b.width + c.width == a.height) {
      // output the result
      out.println(a.height)
      for (i <- 0 until a.height) {
        for (j <- 0 until a.height) {
          if (j < a.width) {
            out.print("A")
          } else if (j < a.width + b.width) {
            out.print("B")
          } else {
            out.print("C")
          }
        }
        out.println()
      }
      return 0
    }
    val rects = new Array[Rectangle](3)
    rects(0) = a
    rects(1) = b
    rects(2) = c
    val reversedRects = new Array[Rectangle](3)
    reversedRects(0) = a1
    reversedRects(1) = b1
    reversedRects(2) = c1
    for (i <- 0 until 3) {
      if (checkRects(i, rects)) {
        return 0
      }
    }
    for (i <- 0 until 3) {
      if (checkRects(i, reversedRects)) {
        return 0
      }
    }
    out.println(-1)
    return 0
  }
}
