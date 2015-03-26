package org.mystic.codeforces.gyms.gym100083

import java.util._
import java.io._
import java.util

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
    return java.lang.Long.parseLong(next)
  }

  def solve: Int = {
    val n = nextInt
    val graph = new util.ArrayList[List[Int]]()
    for (i <- 0 until n) {
      graph.add(new util.ArrayList[Int]())
    }
    var root = -1
    for (i <- 0 until n) {
      val s = nextInt
      if (s != 0) {
        graph.get(s - 1).add(i)
      } else {
        root = i
      }
    }
    object Helper {

      val d = new Array[Int](n)
      val f = new Array[Int](n)
      val used = new Array[Boolean](n)
      var time: Int = 0

      def isParent(from: Int, to: Int): Boolean = {
        return d(to) > d(from) && f(to) < f(from)
      }

      def dfs(v: Int): Unit = {
        used(v) = true
        time += 1
        d(v) = time
        for (i <- 0 until graph.get(v).size()) {
          if (!used(graph.get(v).get(i))) {
            dfs(graph.get(v).get(i))
          }
        }
        time += 1
        f(v) = time
      }
    }

    val m = nextInt
    Helper.time = 0
    Helper.dfs(root)
    for (i <- 0 until m) {
      val a = nextInt - 1
      val b = nextInt - 1
      if (Helper.isParent(a, b)) {
        out.println(1)
      } else {
        out.println(0)
      }
    }
    return 1
  }

  def main(args: Array[String]): Unit = {
//    br = new BufferedReader(new InputStreamReader(System.in))
//    out = new PrintWriter(new BufferedOutputStream(System.out))
    br = new BufferedReader(new InputStreamReader(new FileInputStream("ancestor.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("ancestor.out")))
    solve
    out.close
  }
}
