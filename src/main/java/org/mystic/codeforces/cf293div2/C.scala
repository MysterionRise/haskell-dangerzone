package org.mystic.codeforces.cf293div2

import java.io._
import java.util
import java.util._

object C {

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
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val k = nextInt
    //    val screenNumber = new Array[Int](n)
    val rank = new Array[Int](n)
    val valueByRank = new util.HashMap[Int, Int]()
    for (i <- 0 until n) {
      val j: Int = nextInt - 1
      //      screenNumber(j) = Math.ceil(i / k).toInt
      rank(j) = i
      valueByRank.put(i, j)
    }
    var ans = 0L
    for (i <- 0 until m) {
      val b = nextInt - 1
      ans += Math.ceil(rank(b) / k).toInt + 1
      if (b != 0) {
        val next = valueByRank.get(rank(b) - 1)
        val pS = rank(b)
        val pN = rank(next)
        rank(b) = pN
        rank(next) = pS
        valueByRank.put(pN, b)
        valueByRank.put(pS, next)
      }
    }
    out.println(ans)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
