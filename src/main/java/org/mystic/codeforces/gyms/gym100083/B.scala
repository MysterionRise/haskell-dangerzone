package org.mystic.codeforces.gyms.gym100083

import java.util._
import java.io._
import java.util

object B {

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
    val graph = new Array[util.ArrayList[Int]](n)
    (0 until n).foreach(i => graph(i) = new util.ArrayList[Int]())
    val m = nextInt
    (0 until m).foreach(_ => graph(nextInt - 1).add(nextInt - 1))
    val g = new Array[Array[Int]](n)
    (0 until n).foreach({
      i =>
        g(i) = new Array[Int](graph(i).size())
        g(i).indices.foreach(j => g(i)(j) = graph(i).get(j))
    })

    object Helper {

      val color = new Array[Char](n)
      val path = new util.ArrayList[Int]()

      def findCycle(v: Int): Boolean = {
        if (color(v) == 'b') {
          return false
        }
        if (color(v) == 'g') {
          path.add(v)
          return true
        }
        color(v) = 'g'
        g(v).indices.foreach({
          i =>
            if (findCycle(g(v)(i))) {
              path.add(v)
              color(g(v)(i)) = 'b'
              return true
            }
        })
        color(v) = 'b'
        false
      }
    }
    (0 until n).foreach({
      i =>
        if (Helper.findCycle(i)) {
          val end = Helper.path.get(0)
          out.println("YES")
          val len = Helper.path.size()
          (len - 1 to 1 by -1).dropWhile(i => Helper.path.get(i) != end).foreach(i => out.print((Helper.path.get(i) + 1) + " "))
          return 1
        }
    })
    out.println("NO")
    1
  }

  def main(args: Array[String]): Unit = {
//    br = new BufferedReader(new InputStreamReader(System.in))
//    out = new PrintWriter(new BufferedOutputStream(System.out))
        br = new BufferedReader(new InputStreamReader(new FileInputStream("cycle2.in")))
        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("cycle2.out")))
    solve
    out.close
  }
}
