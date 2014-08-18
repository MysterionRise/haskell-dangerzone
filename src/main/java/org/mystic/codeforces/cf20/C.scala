package org.mystic.codeforces.cf20

import java.util
import java.util._
import java.io._

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
    val g = new ArrayList[List[(Int, Long)]]
    for (i <- 0 until n) {
      g.add(new util.ArrayList[(Int, Long)]())
    }
    for (i <- 0 until m) {
      val a = nextInt - 1
      val b = nextInt - 1
      val len = nextLong
      g.get(a).add((b, len))
      g.get(b).add((a, len))
    }
    val graph = new Array[Array[(Int, Long)]](n)
    for (i <- 0 until n) {
      graph(i) = new Array[(Int, Long)](g.get(i).size)
      val len = graph(i).length
      for (j <- 0 until len) {
        graph(i)(j) = g.get(i).get(j)
      }
    }


    object Dijkstra {
      val path = new Array[Int](n)

      def findShortestPath(start: Int, end: Int): (Long, Array[Int]) = {
        val dist = new Array[Long](n)
        val relaxed = new Array[Boolean](n)
        Arrays.fill(dist, Long.MaxValue)
        dist(start) = 0
        val set = new util.PriorityQueue[Edge]()
        val path = new Array[Int](n)
        Arrays.fill(path, -1)
        set.add(new Edge(dist(start), start))
        while (!set.isEmpty) {
          val v = set.poll().to
          if (!relaxed(v)) {
            for (i <- 0 until graph(v).length) {
              val to = graph(v)(i)._1
              val len = graph(v)(i)._2
              if (dist(v) + len < dist(to)) {
                relaxed(v) = true
                dist(to) = dist(v) + len
                path(to) = v
                set.add(new Edge(dist(to), to))
              }
            }
          }
        }
        return (dist(end), path)
      }
    }
    val (dist, p) = Dijkstra.findShortestPath(0, n - 1)
    if (dist == Long.MaxValue) {
      out.println(-1)
    } else {
      val path = new util.ArrayList[Int]()
      var v = n - 1
      while (v != 0) {
        path.add(v + 1)
        v = p(v)
      }
      path.add(1)
      for (i <- path.size - 1 to 0 by -1) {
        out.print(path.get(i) + " ")
      }
    }
    return 1
  }

  class Edge(var len: Long, var to: Int) extends Comparable[Edge] {

    override def compareTo(o: Edge): Int = -java.lang.Long.compare(o.len, this.len)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
