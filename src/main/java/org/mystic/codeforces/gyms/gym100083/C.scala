package org.mystic.codeforces.gyms.gym100083

import java.util._
import java.io._
import java.util

/**
 * TLed solution
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

  def nextLong: Long = {
    java.lang.Long.parseLong(next)
  }

  def comp(a: Int, b: Int): Boolean = {
    a < b
  }

  def solve: Int = {
    val n = nextInt
    val graph = new Array[util.ArrayList[Int]](n)
    for (i <- 0 until n) {
      graph(i) = new util.ArrayList[Int]()
    }
    val m = nextInt
    val edges = new util.HashMap[(Int, Int), Int]()
    for (i <- 1 to m) {
      val a: Int = nextInt - 1
      val b: Int = nextInt - 1
      edges.put((Math.min(a, b), Math.max(a, b)), i)
      graph(a).add(b)
      graph(b).add(a)
    }
    val g = new Array[Array[Int]](n)
    for (i <- 0 until n) {
      g(i) = new Array[Int](graph(i).size())
      for (j <- g(i).indices) {
        g(i)(j) = graph(i).get(j)
      }
    }

    object Helper {

      val inTime = new Array[Int](n)
      val low = new Array[Int](n)
      val used = new Array[Boolean](n)
      val bridges = new util.ArrayList[(Int, Int)]()
      var timer = 0

      def findBridge(u: Int, v: Int): Unit = {
        used(v) = true
        inTime(v) = timer
        low(v) = timer
        timer += 1
        for (i <- g(v).indices) {
          val w = g(v)(i)
          if (!used(w)) {
            findBridge(v, w)
            low(v) = Math.min(low(v), low(w))
            if (low(w) == inTime(w)) {
              bridges.add((v, w))
            }
          }
          else {
            if (w != u)
              low(v) = Math.min(low(v), inTime(w))
          }
        }
      }
    }

    Arrays.fill(Helper.used, false)
    for (i <- 0 until n) {
      if (!Helper.used(i)) {
        Helper.findBridge(i, i)
      }
    }
    val size: Int = Helper.bridges.size()
    out.println(size)
    val edgeNumbers = new Array[Int](size)
    for (i <- 0 until size) {
      val a = Helper.bridges.get(i)._1
      val b = Helper.bridges.get(i)._2
      edgeNumbers(i) = edges.get((Math.min(a, b), Math.max(a, b)))
    }
    val sorted = edgeNumbers.sortWith(comp)
    for (i <- sorted.indices) {
      out.println(sorted(i))
    }
    1
  }

  def main(args: Array[String]): Unit = {
    //        br = new BufferedReader(new InputStreamReader(System.in))
    //        out = new PrintWriter(new BufferedOutputStream(System.out))
    br = new BufferedReader(new InputStreamReader(new FileInputStream("bridges.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("bridges.out")))
    solve
    out.close
  }
}
