package org.mystic.codeforces.cf0_100.cf10

import java.util._
import java.lang._
import java.io._

object B {

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

  def solve = {
    val n = nextInt
    val k = nextInt
    val cinema = new Array[Array[Int]](k)
    val xc = Math.ceil(k / 2).toInt
    for (i <- 0 until k) {
      cinema(i) = new Array[Int](k)
    }
    for (i <- 0 until k) {
      for (j <- 0 until k) {
        cinema(i)(j) = func(xc, i, j)
      }
    }
    for (t <- 0 until n) {
      val m = nextInt
      var sum = Int.MaxValue
      var start = 0
      var row = 0
      for (i <- 0 until k) {
        var interSum = 0
        for (j <- 0 until m) {
          interSum += cinema(i)(j)
        }
        if (interSum < sum) {
          sum = interSum
          row = i
          start = 0
        }
        for (j <- m until k) {
          interSum += cinema(i)(j)
          interSum -= cinema(i)(j - m)
          if (interSum < sum) {
            sum = interSum
            row = i
            start = j - m + 1
          }
        }
      }
      if (sum < 100000) {
//        out.println(sum)
        out.println((row + 1) + " " + (start + 1) + " " + (start + m))
        for (j <- start until start + m) {
          cinema(row)(j) = 100000
        }
      } else {
        out.println(-1)
      }
//      for (i <- 0 until k) {
//        for (j <- 0 until k) {
//          out.print(cinema(i)(j) + " ")
//        }
//        out.println
//      }
    }
  }

  def func(xc: Int, x: Int, y: Int) = {
    Math.abs(x - xc) + Math.abs(y - xc)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
