package org.mystic.codeforces.cf0_100.cf14div2

import java.util
import java.util._
import java.io._

object D {

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
    java.lang.Integer.parseInt(next)
  }

  def nextLong: Long = {
    java.lang.Long.parseLong(next)
  }

  def addEdge(edge: (Int, Int), graph: Array[List[Int]]): Array[List[Int]] = {
    graph(edge._1).add(edge._2)
    graph(edge._2).add(edge._1)
    graph
  }

  def solve = {
    val n = nextInt
    val edges = new Array[(Int, Int)](n - 1)
    var graph = new Array[List[Int]](n)
    for (i <- 0 until n) {
      graph(i) = new ArrayList[Int]()
    }
    for (i <- 0 until n - 1) {
      edges(i) = (nextInt - 1, nextInt - 1)
      graph = addEdge(edges(i), graph)
    }
    var ans = Int.MinValue
    for (i <- 0 until n - 1) {
      val first = edges(i)._1
      val second = edges(i)._2
      val used = new Array[Boolean](n)
      val dist = new Array[Int](n)

      object Helper {
        def bfs(start: Int) = {
          val q: util.Queue[Int] = new util.LinkedList[Int]()
          q.add(start)
          dist(start) = 0
          while (!q.isEmpty) {
            val v = q.poll()
            used(v) = true
            for (i <- 0 until graph(v).size()) {
              val next: Int = graph(v).get(i)
              if (!used(next)) {
                dist(next) = dist(v) + 1
                q.add(next)
              }
            }
          }
        }

        def cleanUpWith(vert: Int) = {
          Arrays.fill(used, false)
          Arrays.fill(dist, -1)
          used(vert) = true
        }
      }

      var firstDiameter = 0
      Helper.cleanUpWith(first)
      Helper.bfs(second)
      val max1 = dist.zipWithIndex.max
      Helper.cleanUpWith(first)
      Helper.bfs(max1._2)
      firstDiameter = dist.max

      var secondDiameter = 0
      Helper.cleanUpWith(second)
      Helper.bfs(first)
      val max2 = dist.zipWithIndex.max
      Helper.cleanUpWith(second)
      Helper.bfs(max2._2)
      secondDiameter = dist.max

      ans = Math.max(ans, firstDiameter * secondDiameter)
    }
    out.println(ans)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
