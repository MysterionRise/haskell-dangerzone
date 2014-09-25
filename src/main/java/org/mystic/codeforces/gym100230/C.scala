package org.mystic.codeforces.gym100230

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
    val m = nextInt
    val s = nextInt - 1
    val edges = new Array[(Int, Int, Long)](m)
    (0 until m).foreach(i => edges(i) = (nextInt - 1, nextInt - 1, nextLong))
    object Helper {

      val d = new Array[Long](n)

      val INF: Long = Long.MaxValue
      val INF1: Long = INF - 1

      def findShortestPathsFrom(v: Int) = {
        Arrays.fill(d, INF)
        d(v) = 0
        (0 until n).foreach({
          i =>
            (0 until m).foreach({
              j =>
                if (d(edges(j)._1) < INF) {
                  d(edges(j)._2) = Math.min(d(edges(j)._2), d(edges(j)._1) + edges(j)._3)
                }
            })
        })
        val e = new Array[Long](n)
        (0 until n).foreach(i => e(i) = d(i))
        (0 until m).foreach({
          j =>
            if (d(edges(j)._1) < INF) {
              d(edges(j)._2) = Math.min(d(edges(j)._2), d(edges(j)._1) + edges(j)._3)
            }
        })
        (0 until n).foreach({
          i =>
            if(d(i) < e(i)) {
              d(i) = INF1
            }
        })

      }

      def format(ans: Long): String = ans match {
        case INF => "*"
        case INF1 => "-"
        case _ => ans.toString
      }
    }
    Helper.findShortestPathsFrom(s)
    (0 until n).foreach({
      i =>
        out.println(Helper.format(Helper.d(i)))
    })
    return 1
  }

  def main(args: Array[String]): Unit = {
//    br = new BufferedReader(new InputStreamReader(System.in))
//        out = new PrintWriter(new BufferedOutputStream(System.out))
        br = new BufferedReader(new InputStreamReader(new FileInputStream("path.in")))
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("path.out")))
    solve
    out.close
  }
}
