package org.mystic.codeforces.cf0_100.cf4div2

import java.util._
import java.lang._
import java.io._
import java.util

object D {

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

  def comp(x: ((Int, Int), Int), y: ((Int, Int), Int)): Boolean = {
    val s1: Long = Int.int2long(x._1._1) * Int.int2long(x._1._2)
    val s2: Long = Int.int2long(y._1._1) * Int.int2long(y._1._2)
    s1 < s2
  }

  def couldInsert(envelope1: (Int, Int), envelope2: (Int, Int)): Boolean = {
    envelope1._1 < envelope2._1 && envelope1._2 < envelope2._2
  }

  def couldInsert2(envelope1: ((Int, Int), Int), envelope2: ((Int, Int), Int)): Boolean = {
    envelope1._1._1 < envelope2._1._1 && envelope1._1._2 < envelope2._1._2
  }

  def solve = {
    val n = nextInt
    val postcard = (nextInt, nextInt)
    val envelopes = new Array[(Int, Int)](n)
    for (i <- 0 until n) {
      envelopes(i) = (nextInt, nextInt)
    }
    val cleanedEnvelopes = new util.ArrayList[((Int, Int), Int)]()
    for (i <- 0 until n) {
      if (couldInsert(postcard, envelopes(i))) {
        cleanedEnvelopes.add((envelopes(i), i))
      }
    }
    var zippedEnv = cleanedEnvelopes.toArray(new Array[((Int, Int), Int)](0))
    zippedEnv = zippedEnv.sortWith(comp)
//    zippedEnv.foreach(print)
    val len = zippedEnv.length
    if (len == 0) {
      out.println(0)
    } else {
      val d = new Array[Int](len)
      val p = new Array[Int](len)
      d(0) = 1
      p(0) = -1
      for (i <- 1 until len) {
        val a = zippedEnv(i)
        var max = -1
        var pj = -1
        for (j <- 0 until i) {
          if (couldInsert2(zippedEnv(j), a)) {
            if (d(j) > max) {
              max = d(j)
              pj = j
            }
          }
        }
        if (max + 1 > 1) {
          p(i) = pj
          d(i) = max + 1
        } else {
          p(i) = -1
          d(i) = 1
        }
      }
      //    d.foreach(x => print(x + " "))
      //    p.foreach(x => print(x + " "))
      var max = -1
      var start = -1
      for (i <- 0 until len) {
        if (max < d(i)) {
          max = d(i)
          start = i
        }
      }
      out.println(max)
      val ans = new Array[Int](max)
      var ind = 0
      while (start != -1) {
        ans(ind) = zippedEnv(start)._2 + 1
        ind += 1
        start = p(start)
      }
      for (i <- 0 until max) {
        out.print(ans(max - i - 1) + " ")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
