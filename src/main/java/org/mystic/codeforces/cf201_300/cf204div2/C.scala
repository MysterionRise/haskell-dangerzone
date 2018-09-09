package org.mystic.codeforces.cf201_300.cf204div2

import java.io._
import java.util.StringTokenizer
import java.lang.Double
import java.text.DecimalFormat

/**
 * @todo fail solution
 */
object C {
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

  def nextDouble: Double = {
    Double.parseDouble(next)
  }

  def solve = {
    val n = nextInt * 2
    val a = new Array[Double](n)
    var sum1 = 0.0
    var sum2 = 0.0
    var up = 0
    var down = 0
    for (i <- 0 until n) {
      a(i) = nextDouble
      sum1 += a(i)
      if (a(i) - Math.floor(a(i)) > 0.5) {
        up += 1
      } else {
        down += 1
      }
    }
    if (down >= up) {
      for (i <- 0 until n) {
        if (a(i) - Math.floor(a(i)) > 0.5) {
          sum2 += Math.ceil(a(i))
        } else {
          sum2 += Math.floor(a(i))
        }
      }
      val myFormatter = new DecimalFormat("0.000")
      val output = myFormatter.format(Math.abs(sum1 - sum2))
      out.println(output)
    } else {
      for (i <- 0 until n) {
        if (a(i) - Math.floor(a(i)) > 0.5) {
          sum2 += Math.ceil(a(i))
        } else {
          sum2 += Math.floor(a(i))
        }
      }
      val myFormatter = new DecimalFormat("0.000")
      val output = myFormatter.format(Math.abs(sum1 - sum2))
      out.println(output)
    }
  }


  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}