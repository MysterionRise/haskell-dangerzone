package org.mystic.codeforces.abbyy.cup30.finals

import collection.mutable.HashMap
import java.util._
import java.lang._
import java.io._

object A {

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
    val beauty = new Array[Int](n)
    val map = new HashMap[Int, ArrayList[Int]]
    for (i <- 0 to n - 1) {
      beauty(i) = nextInt
      val some = map.get(beauty(i))
      some match {
        case Some(x) => {
          x add i
          map put(beauty(i), x)
        }
        case None => {
          val x = new ArrayList[Int]()
          x add i
          map put(beauty(i), x)
        }
      }
    }
    //println(map.toString)
    val partialSums = new Array[Long](n + 1)
    val partialAllSums = new Array[Long](n + 1)
    partialSums(0) = 0
    partialSums(n) = 0
    partialAllSums(0) = 0
    partialAllSums(n) = 0
    for (i <- 1 to n) {
      if (beauty(i - 1) > 0) {
        partialSums(i) = partialSums(i - 1) + beauty(i - 1)
      } else {
        partialSums(i) = partialSums(i - 1)
      }
      partialAllSums(i) = partialAllSums(i - 1) + beauty(i - 1)
      //print(partialAllSums(i) + " ")
    }
    //println
    var maxAns = Long.MIN_VALUE
    var begin = -1
    var end = -1
    val it = map.keysIterator
    while (it.hasNext) {
      val key = it.next
      val arr = map.get(key).get
      if (arr.size > 1) {
        val min = arr.get(0)
        val max = arr.get(arr.size - 1) + 1
        val res = partialSums(max - 1) - partialSums(min + 1) + beauty(max - 1) + beauty(min)
        if (res > maxAns) {
          maxAns = res
          begin = min
          end = max - 1
        }
      }
    }
    out.print(maxAns)
    var countToKill = 0
    val killed = new ArrayList[Int]
    for (i <- 0 until begin) {
      countToKill = countToKill + 1
      killed.add(i + 1)
    }
    for (i <- begin + 1 until end) {

      if (beauty(i) < 0) {
        countToKill = countToKill + 1
        killed.add(i + 1)
      }
    }

    for (i <- end + 1 until n) {
      countToKill = countToKill + 1
      killed.add(i + 1)
    }
    out.println(" " + countToKill)
    for (i <- 0 to killed.size - 1) {
      out.print(killed.get(i) + " ")
    }

  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close

  }
}
