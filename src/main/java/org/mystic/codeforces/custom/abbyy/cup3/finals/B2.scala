package org.mystic.codeforces.custom.abbyy.cup3.finals

import java.io.{BufferedOutputStream, InputStreamReader, BufferedReader, PrintWriter}
import java.util.StringTokenizer
import java.lang.{Long, String}
import scala.Array
import scala.math._
import scala.Predef._

object B2 {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

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

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  var tree: Array[Long] = null

  def solve = {
    val n = nextInt
    val arr = new Array[Int](n)
    val pos = new Array[Int](n)
    for (i <- 0 until n) {
      arr(i) = nextInt
      pos(arr(i) - 1) = i
    }
    tree = new Array[Long](4 * n)
    for (i <- 0 until 4 * n) {
      tree(i) = 0
    }
    val sumArray = new Array[Int](n)
    sumArray(0) = 0
    for (i <- 1 until n) {
      if (arr(i) < arr(i - 1)) {
        sumArray(i) = 1
      }
    }
    buildSegmentTree(sumArray, 1, 0, n - 1)
    for (i <- 0 until 4 * n) {
      print(tree(i) + " ")
    }
    println
    val q = nextInt
    for (qq <- 0 until q) {
      val typeOfQuery = nextInt
      val a = nextInt
      val b = nextInt
      typeOfQuery match {
        case 1 => {
          println("a = " + a + " b = " + b)
          val left = min(pos(a - 1), pos(b - 1))
          val right = max(pos(a - 1), pos(b - 1))
          println("call sum func with " + left + " " + right)
          println(sum(1, 0, n - 1, left, right) + 1)
        }
        case 2 => {
          val x = a - 1
          val y = b - 1
          val e1 = arr(x)
          val e2 = arr(y)
          val tmp = arr(a - 1)
          arr(a - 1) = arr(b - 1)
          arr(b - 1) = tmp
          val temp = pos(e1 - 1)
          pos(e1 - 1) = pos(e2 - 1)
          pos(e2 - 1) = temp
          if (x > 1) {
            if (arr(x - 1) < arr(x - 2)) {
              updateVal(1, 0, n - 1, x - 1, 1)
              // need to update value by index x - 1
            } else {
              updateVal(1, 0, n - 1, x - 1, 0)
            }
          }
          if (x > 0) {
            if (arr(x) < arr(x - 1)) {
              updateVal(1, 0, n - 1, x, 1)
              // need to update value by index x
            } else {
              updateVal(1, 0, n - 1, x, 0)
            }
          }
          if (x < n - 1) {
            if (arr(x) < arr(x + 1)) {
              updateVal(1, 0, n - 1, x + 1, 1)
              // need to update value by index x + 1
            } else {
              updateVal(1, 0, n - 1, x + 1, 0)
            }
          }
          if (y > 1) {
            if (arr(y - 1) < arr(y - 2)) {
              updateVal(1, 0, n - 1, y - 1, 1)
              // need to update value by index y - 1
            } else {
              updateVal(1, 0, n - 1, y - 1, 0)
            }
          }
          if (y > 0) {
            if (arr(y) < arr(y - 1)) {
              updateVal(1, 0, n - 1, y, 1)
              // need to update value by index y
            } else {
              updateVal(1, 0, n - 1, y, 0)
            }
          }
          if (y < n - 1) {
            if (arr(y) < arr(y + 1)) {
              updateVal(1, 0, n - 1, y + 1, 1)
              // need to update value by index y + 1
            } else {
              updateVal(1, 0, n - 1, y + 1, 0)
            }
          }
          for (i <- 0 until 4 * n) {
            print(tree(i) + " ")
          }
          println
        }
      }
    }
  }

  def updateVal(vertex: Int, left: Int, right: Int, pos: Int, newVal: Long): Unit = {
    if (left == right)
      tree(vertex) = newVal
    else {
      val median = (left + right) / 2
      if (pos <= median)
      // we goes on the left subling
        updateVal(vertex * 2, left, median, pos, newVal)
      else
      //we goes to the right
        updateVal(vertex * 2 + 1, median + 1, right, pos, newVal)
      tree(vertex) = tree(vertex * 2) + tree(vertex * 2 + 1)
    }
  }


  def buildSegmentTree(arr: Array[Int], vertex: Int, left: Int, right: Int): Unit = {
    if (left == right) {
      tree(vertex) = arr(left)
    } else {
      // goes to the left
      buildSegmentTree(arr, vertex * 2, left, (left + right) / 2)
      // goes to the right
      buildSegmentTree(arr, vertex * 2 + 1, (left + right) / 2 + 1, right)
      tree(vertex) = tree(vertex * 2) + tree(vertex * 2 + 1)
    }
  }

  def sum(vertex: Int, boundL: Int, boundR: Int, left: Int, right: Int): Long = {
    if (left > right) {
      0
    } else if (left == boundL && right == boundR) {
      tree(vertex)
    } else {
      val median = (boundL + boundR) / 2
      sum(vertex * 2, boundL, median, left, min(right, median)) +
        sum(vertex * 2 + 1, median + 1, right, max(left, median + 1), right)
    }
  }

}
