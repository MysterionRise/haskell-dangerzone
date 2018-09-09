package org.mystic.codeforces.cf0_100.cf14div2

import java.util._
import java.io._
import java.util

object C {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextLong: Long = {
    java.lang.Long.parseLong(next)
  }

  def isParralel(x: ((Long, Long), (Long, Long))): Boolean = {
    x._1._1 == x._2._1 || x._1._2 == x._2._2
  }

  def dist(x: (Long, Long), y: (Long, Long)): Long = {
    (x._1 - y._1) * (x._1 - y._1) + (x._2 - y._2) * (x._2 - y._2)
  }

  def angleBetweenLines(a: (Long, Long), b: (Long, Long), c: (Long, Long), d: (Long, Long)): Boolean = {
    if (a == c) {
      return dist(a, b) + dist(a, d) == dist(b, d)
    } else if (a == d) {
      return dist(a, b) + dist(a, c) == dist(b, c)
    } else if (b == c) {
      return dist(a, b) + dist(b, d) == dist(a, d)
    } else if (b == d) {
      return dist(a, b) + dist(b, c) == dist(a, c)
    }
    false
  }

  def solve = {
    val dots = new util.HashSet[(Long, Long)]()
    val lines = new Array[((Long, Long), (Long, Long))](4)
    for (i <- 0 until 4) {
      lines(i) = ((nextLong, nextLong), (nextLong, nextLong))
      dots.add(lines(i)._1)
      dots.add(lines(i)._2)
    }
    var flag = true
    if (dots.size() != 4) {
      flag = false
    }
    var parralel = 0
    val dotArray = dots.toArray(new Array[(Long, Long)](0))
    for (i <- 0 until 4) {
      if (isParralel(lines(i))) {
        parralel += 1
      }
    }
    if (parralel != 4) {
      flag = false
    }
    var numberOf90 = 0
    for (i <- 0 until 4) {
      for (j <- 0 until 4) {
        if (angleBetweenLines(lines(i)._1, lines(i)._2, lines(j)._1, lines(j)._2)) {
          numberOf90 += 1
        }
      }
    }
    if (numberOf90 != 8) {
      flag = false
    }
    for (i <- 0 until 4) {
      if (!dots.contains(lines(i)._1)) {
        flag = false
      }
      if (!dots.contains(lines(i)._2)) {
        flag = false
      }
      if (lines(i)._1 == lines(i)._2) {
        flag = false
      }
    }
    var numOfLines = 0
    for (i <- 0 until dotArray.length) {
      for (j <- 0 until dotArray.length) {
        if (lines.contains((dotArray(i), dotArray(j)))) {
          numOfLines += 1
        }
      }
    }
    if (numOfLines != 4) {
      flag = false
    }

    if (flag) {
      out.print("YES")
    } else {
      out.print("NO")
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
