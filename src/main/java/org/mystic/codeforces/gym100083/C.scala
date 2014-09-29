package org.mystic.codeforces.gym100083

import java.util._
import java.io._
import java.util

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
    val graph = new Array[util.ArrayList[Int]](n)
    (0 until n).foreach(i => graph(i) = new util.ArrayList[Int]())
    val m = nextInt
    val edges = new util.HashMap[(Int, Int), Int]()
    (1 to m).foreach({
      i =>
        val a: Int = nextInt - 1
        val b: Int = nextInt - 1
        edges.put((Math.min(a, b), Math.max(a, b)), i)
        graph(a).add(b)
        graph(b).add(a)
    })
    val g = new Array[Array[Int]](n)
    (0 until n).foreach({
      i =>
        g(i) = new Array[Int](graph(i).size())
        (0 until g(i).length).foreach({ j =>
          g(i)(j) = graph(i).get(j)
        })
    })

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
        (0 until g(v).length).foreach({
          i =>
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
                low(v) = Math.min(low(v), inTime(w));
            }
        })
      }
    }

    Arrays.fill(Helper.used, false)
    (0 until n).foreach({
      i =>
        if (!Helper.used(i)) {
          Helper.findBridge(i, i)
        }
    })
    out.println(Helper.bridges.size())
    (0 until Helper.bridges.size()).foreach({
      i =>
        val a = Helper.bridges.get(i)._1
        val b = Helper.bridges.get(i)._2
        out.println(edges.get((Math.min(a, b), Math.max(a, b))))
    })
    return 1
  }

  def main(args: Array[String]): Unit = {
//    br = new BufferedReader(new InputStreamReader(System.in))
//    out = new PrintWriter(new BufferedOutputStream(System.out))
        br = new BufferedReader(new InputStreamReader(new FileInputStream("bridges.in")))
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("bridges.out")))
    solve
    out.close
  }
}
