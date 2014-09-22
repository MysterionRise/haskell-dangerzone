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
    val graph = new Array[util.ArrayList[(Int, Long)]](n)
    (0 until n).foreach(i => graph(i) = new util.ArrayList[(Int, Long)]())
    val m = nextInt
    val s = nextInt - 1
    (0 until m).foreach(_ => graph(nextInt - 1).add((nextInt - 1, nextInt)))
    val g = new Array[Array[(Int, Long)]](n)
    (0 until n).foreach({
      i =>
        g(i) = new Array[(Int, Long)](graph(i).size())
        (0 until g(i).length).foreach(j => g(i)(j) = (1, 2.toLong))
    })

    object Helper {

      val d = new Array[Long](n)

      def findShortestPathsFrom(v: Int) = {
        Arrays.fill(d, Long.MaxValue)
        d(v) = 0

      }

      def format(ans: Long): String = ans match {
        case -1 => "*"
        case -2 => "-"
        case _ => ans.toString
      }
    }
    (0 until n).foreach({
      i =>
        if (i != s) out.println(Helper.format(Helper.d(i)))
    })
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    //    br = new BufferedReader(new InputStreamReader(new FileInputStream("path.in")))
    //    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("path.out")))
    solve
    out.close
  }
}
