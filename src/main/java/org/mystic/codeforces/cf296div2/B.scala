package org.mystic.codeforces.cf296div2

import java.io._
import java.util
import java.util._

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
    val s = next.toCharArray
    val t = next.toCharArray
    var curr = 0
    val t1 = new util.HashMap[Character, ArrayList[Int]]()
    for (i <- 0 until n) {
      if (s(i) != t(i)) {
        curr += 1
        if (!t1.containsKey(t(i))) {
          val list = new util.ArrayList[Int]()
          list.add(i)
          t1.put(t(i), list)
        } else {
          t1.get(t(i)).add(i)
        }
      }
    }
    var i1 = -1
    var i2 = -1
    var min = curr
    for (i <- 0 until n) {
      if (s(i) != t(i)) {
        if (null != t1.get(s(i))) {
          val ind = t1.get(s(i))
          for (j <- 0 until ind.size()) {
            if (s(ind.get(j)) == t(i)) {
              if (curr - 2 < min) {
                min = curr - 2
                i1 = i
                i2 = ind.get(j)
                out.println(min)
                out.println((i1 + 1) + " " + (i2 + 1))
                return 1
              }
            } else {
              if (curr - 1 < min) {
                min = curr - 1
                i1 = i
                i2 = ind.get(j)
              }
            }
          }
        }
      }
    }
    if (min == curr) {
      out.println(curr)
      out.println("-1 -1")
    } else {
      out.println(min)
      out.println((i1 + 1) + " " + (i2 + 1))
    }
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
