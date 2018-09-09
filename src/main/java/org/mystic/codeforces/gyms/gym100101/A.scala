package org.mystic.codeforces.gyms.gym100101

import java.util._
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
    java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val x = nextInt
    val y = nextInt
    var a0 = nextInt
    val m = nextInt
    val z = nextInt
    val t = nextInt
    val b0 = nextInt
    val two16: Int = Math.pow(2, 16).toInt
    val two30: Int = Math.pow(2, 30).toInt
    val prefixSum = new Array[Long](n + 1)
    var sum: Long = a0
    prefixSum(0) = 0
    for (i <- 1 until n) {
      val ai = (((x.toLong * a0) % two16 + y) % two16).toInt
      prefixSum(i) = sum
      sum += ai
      a0 = ai
    }
    prefixSum(n) = sum
    sum = 0
    var c0 = b0 % n
    var bi1 = b0
    for (i <- 1 until 2 * m) {
      var zz = ((z.toLong * bi1) % two30 + t) % two30
      if (zz < 0) {
        zz += two30
      }
      var b1 = zz % n
      if (b1 < 0) {
        b1 += n
      }
      val c1 = b1.toInt
      bi1 = zz.toInt
      if (i % 2 == 1) {
        sum += (prefixSum(Math.max(c0, c1) + 1) - prefixSum(Math.min(c0, c1)))
      } else {
        c0 = c1
      }
    }
    out.println(sum)
    1
  }

  def main(args: Array[String]): Unit = {
            br = new BufferedReader(new InputStreamReader(System.in))
            out = new PrintWriter(new BufferedOutputStream(System.out))
//    br = new BufferedReader(new InputStreamReader(new FileInputStream("sum0.in")))
//    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("sum0.out")))
    solve
    out.close
  }
}
