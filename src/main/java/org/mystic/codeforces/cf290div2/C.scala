package org.mystic.codeforces.cf290div2

import java.io._
import java.util
import java.util._

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

  def compare(s: String, t: String): (Int, Int) = {
    val len = Math.min(s.length, t.length)
    (0 until len).foreach(i => {
      val t1 = t.charAt(i)
      val s1 = s.charAt(i)
      if (s1 != t1) {
        return (t1 - 'a', s1 - 'a')
      }
    })
    if (t.length > s.length) {
      return (0, 0)
    }
    return (-1, -1)
  }

  def solve: Int = {
    val n = nextInt
    val names = new Array[String](n)
    (0 until n).foreach(names(_) = next)
    val len = 26
    val g = new Array[Array[Boolean]](27)
    (0 until len).foreach(g(_) = new Array[Boolean](len))
    (0 until n - 1).foreach(i => {
      val edge = compare(names(i), names(i + 1))
      if (edge ==(-1, -1)) {
        out.println("Impossible")
        return 1
      }
      if (edge !=(0, 0)) {
        g(edge._1)(edge._2) = true
      }
    })
    var flag = true
    val color = new Array[Int](len)
    val ans = new util.ArrayList[Int]()
    class Utils {
      def dfs(v: Int): Unit = {
        color(v) = 1
        (0 until len).foreach(i => {
          if (g(v)(i))
            if (color(i) == 0) {
              dfs(i)
            } else if (color(i) == 1) {
              flag = false
            }
        })
        color(v) = 2
        ans.add(v)
      }
    }
    val u = new Utils
    for (i <- 0 until len) {
      if (color(i) == 0) {
        u.dfs(i)
      }
    }
    if (!flag) {
      out.println("Impossible")
      return 1
    }
    (0 until ans.size()).foreach(i => out.print((ans.get(i) + 'a').toChar))
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
