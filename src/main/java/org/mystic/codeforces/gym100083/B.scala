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
    val graph = new Array[util.ArrayList[Int]](n)
    for (i <- 0 until n) {
      graph(i) = new util.ArrayList[Int]()
    }
    for (i <- 0 until m) {
      graph(nextInt - 1).add(nextInt - 1)
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
        for (i <- 0 until graph(v).size()) {
          val j: Int = graph(v).get(i)
          if (color(j) != 'b') {
            if (isACycle(j)) {
              path.add(j)
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
    (0 to n).toStream.takeWhile(_ => !flag).foreach(i => flag |= Helper.isACycle(i))
    if (flag) {
      out.println("YES")
      var ind = 1
      out.print((Helper.path.get(0) + 1) + " ")
      while (ind < Helper.path.size) {
        if (Helper.path.get(ind) == Helper.end) {
          ind = Helper.path.size()
        } else {
          out.print((Helper.path.get(ind) + 1) + " ")
          ind += 1
        }
      }
    } else {
      out.println("NO")
    }
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    //            br = new BufferedReader(new InputStreamReader(new FileInputStream("cycle2.in")))
    //        out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("cycle2.out")))
    solve
    out.close
  }
}
