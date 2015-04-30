package org.mystic.codeforces.cf201_300.cf202div2

import java.io._
import java.util.StringTokenizer

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

  def solve = {
    val v = nextInt
    val num: Array[Int] = new Array[Int](9)
    for (i <- 0 until 9) {
      num(i) = nextInt
    }
    var min: Int = Integer.MAX_VALUE
    var pos: Int = -1
    for (i <- 0 until 9) {
      if (num(i) <= min) {
        min = num(i)
        pos = i
      }
    }
    val len: Int = v / min
    if (len == 0) {
      out.println(-1);
    } else {
      val res = StringBuilder.newBuilder
      for (i <- 0 until len) {
        res.append(pos + 1)
      }
      val ch = res.toArray
      var left = v - len * min
      var i = 0
      while (i < len && left > 0) {
        val count = num(ch(i).toInt - 48 - 1)
        var posJ = -1
        for (j <- 0 until 9) {
          if (count + left >= num(j)) {
            posJ = j
          }
        }
        if (posJ != -1) {
          ch(i) = (posJ + 1 + 48).toChar
          left = left + count - num(posJ)
        }
        i = i + 1
      }
      ch.foreach(out.print)
    }

  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
