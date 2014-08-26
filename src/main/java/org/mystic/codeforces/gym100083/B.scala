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
      val p = new Array[Int](n)
      var end: Int = -1

      def isACycle(v: Int): Boolean = {
        if (color(v) == 'g') {
          end = v
          return true
        }
        color(v) = 'g'
        for (i <- 0 until graph(v).size()) {
          val j: Int = graph(v).get(i)
          p(j) = v
          return isACycle(j)
        }
        color(v) = 'b'
        return false
      }
    }
    Arrays.fill(Helper.p, -1)
    Arrays.fill(Helper.color, 'w')
    var flag = false
    for (i <- 0 until n) {
      if (Helper.color(i) == 'w') {
        flag |= Helper.isACycle(i)
      }
    }
    if (flag) {
      out.println("YES")
      var start = Helper.end
      val path = new util.ArrayList[Int]()
      path.add(start)
      while (Helper.p(start) != Helper.end) {
        path.add(Helper.p(start))
        start = Helper.p(start)
      }
      for (i <- path.size - 1 to 0 by -1) {
        out.print((path.get(i) + 1) + " ")
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
