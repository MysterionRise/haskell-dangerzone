package org.mystic.codeforces.gyms.gym100230

import java.util._
import java.io._

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

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val s = nextInt - 1
    val edges = new Array[(Int, Int, Long)](m)
    (0 until m).foreach(i => edges(i) = (nextInt - 1, nextInt - 1, nextLong))
    object Helper {

      val d = new Array[Long](n)

      val INF: Long = Long.MaxValue

      def findShortestPathsFrom(v: Int) = {
        Arrays.fill(d, INF)
        d(v) = 0
        (0 until n).foreach({
          i =>
            (0 until m).foreach({
              j =>
                if (d(edges(j)._1) < INF) {
                  d(edges(j)._2) = Math.min(d(edges(j)._2), d(edges(j)._1) + edges(j)._3)
                  d(edges(j)._2) = Math.max(-INF, d(edges(j)._2))
                }
            })
        })
        val e = new Array[Long](n)
        (0 until n).foreach(i => e(i) = d(i))
        (0 until n).foreach({
          i =>
            (0 until m).foreach({
              j =>
                if (d(edges(j)._1) < INF) {
                  d(edges(j)._2) = Math.min(d(edges(j)._2), d(edges(j)._1) + edges(j)._3)
                  d(edges(j)._2) = Math.max(-INF, d(edges(j)._2))
                }
            })
        })
        (0 until n).foreach({
          i =>
            if (d(i) < e(i)) {
              out.println("-")
            } else {
              if (d(i) == INF) {
                out.println("*")
              } else {
                out.println(d(i))
              }
            }
        })

      }
    }
    Helper.findShortestPathsFrom(s)
    1
  }

  def main(args: Array[String]): Unit = {
//            br = new BufferedReader(new InputStreamReader(System.in))
//                    out = new PrintWriter(new BufferedOutputStream(System.out))
    br = new BufferedReader(new InputStreamReader(new FileInputStream("path.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("path.out")))
    solve
    out.close
  }
}
