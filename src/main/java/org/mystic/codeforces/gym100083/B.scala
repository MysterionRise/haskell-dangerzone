package org.mystic.codeforces.gym100083

import java.util._
import java.io._
import java.util

object B {

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
    val g = new Array[util.ArrayList[Int]](n)
    (0 until n).foreach(i => g(i) = new util.ArrayList[Int]())
    (0 until m).foreach(_ => g(nextInt - 1).add(nextInt - 1))
    val graph = new Array[Array[Int]](n)
    (0 until n).foreach {
      i =>
        graph(i) = new Array[Int](g(i).size())
        (0 until graph(i).length).foreach(j => graph(i)(j) = g(i).get(j))
    }

    object Helper {

      val color = new Array[Char](n)
      val path = new util.ArrayList[Int]()
      var end = -1

      def isACycle(v: Int): Boolean = {
        if (color(v) == 'g') {
          end = v
          return true
        }
        color(v) = 'g'
        for (i <- 0 until graph(v).length) {
          val j: Int = graph(v)(i)
          if (color(j) != 'b') {
            if (isACycle(j)) {
              path.add(j)
              color(j) = 'b'
              return true
            }
          }
        }
        color(v) = 'b'
        return false
      }
    }
    Arrays.fill(Helper.color, 'w')
    var flag = false
    (0 until n).takeWhile(_ => !flag).foreach(i => flag |= Helper.isACycle(i))
    if (flag) {
      val len: Int = Helper.path.size()
      var cnt = 0
      for (i <- 0 until len) {
        if (Helper.path.get(i) == Helper.end) {
          cnt += 1
        }
      }
      out.println("YES")
      if (cnt == 1) {
        (len - 1 to 0 by -1).foreach(i => out.print((Helper.path.get(i) + 1) + " "))
      } else {
        val cleaned = (len - 1 to 0 by -1).dropWhile(i => Helper.path.get(i) != Helper.end)
        out.print((Helper.path.get(cleaned.head) + 1) + " ")
        cleaned.drop(1).takeWhile(i => Helper.path.get(i) != Helper.end).foreach(i => out.print((Helper.path.get(i) + 1) + " "))
      }
    } else {
      out.println("NO")
    }
    return 1
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
