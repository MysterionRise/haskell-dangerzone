package org.mystic.codeforces.ladder6

import java.io._
import java.util
import java.util.StringTokenizer

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object C466 {

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

  def solve: Int = {
    val n = nextInt
    val a = new Array[Int](n)
    val prefixSum = new Array[Int](n)
    val map = new mutable.HashMap[Int, ArrayBuffer[Int]]()
    a(0) = nextInt
    prefixSum(0) = a(0)
    for (i <- 1 until n) {
      a(i) = nextInt
      prefixSum(i) = a(i) + prefixSum(i - 1)
    }
    for (i <- 0 until n) {
      if (map.contains(prefixSum(i))) {
        map.get(prefixSum(i)).get.append(i)
      } else {
        val ab = new ArrayBuffer[Int]()
        ab.append(i)
        map.put(prefixSum(i), ab)
      }
    }
    var ans = 0L
    for (pos <- 1 until n - 1) {
      val sum = prefixSum(pos - 1)
      val left = prefixSum(n - 1) - prefixSum(pos - 1)
      if (left / 2 == sum && left % 2 == 0) {
        val positions = map.getOrElse(2 * sum, ArrayBuffer[Int]()).toArray
        var ind = util.Arrays.binarySearch(positions, pos)
        if (ind < 0) {
          ind = -ind - 1
        }
        val p = positions(ind + 1)
        if (p >= pos && prefixSum(n - 1) - prefixSum(p) == sum) {
          ans += (positions.length - ind)
        }
        if (positions(positions.length - 1) == a.length - 1) {
          ans -= 1
        }
      }
    }
    out.println(ans)
    return 0
  }
}
