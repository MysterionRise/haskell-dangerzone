package org.mystic.codeforces.cf201_300.cf293div2

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
    val s = next.toCharArray
    val t = next.toCharArray
    val m = new scala.collection.mutable.HashMap[Char, Int]()
    for (i <- 0 until t.length) {
      val x = m.getOrElse(t(i), 0)
      m.put(t(i), x + 1)
    }
    var u = 0
    var o = 0
    val missed = new util.ArrayList[Char]()
    for (i <- 0 until s.length) {
      val symb = s(i)
      val x = m.getOrElse(symb, 0)
      if (x > 0) {
        u += 1
        m.put(symb, x - 1)
      } else {
        missed.add(symb)
      }
    }
    for (i <- 0 until missed.size()) {
      val symb = missed.get(i)
      if (Character.isUpperCase(symb)) {
        val lowerCase: Char = Character.toLowerCase(symb)
        val y = m.getOrElse(lowerCase, 0)
        if (y > 0) {
          o += 1
          m.put(lowerCase, y - 1)
        }
      } else {
        val upCase: Char = Character.toUpperCase(symb)
        val y = m.getOrElse(upCase, 0)
        if (y > 0) {
          o += 1
          m.put(upCase, y - 1)
        }
      }

    }
    out.println(u + " " + o)
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
