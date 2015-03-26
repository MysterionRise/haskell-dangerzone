package org.mystic.ir.bayan.shortcut2014

import java.io._
import java.util._

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
    val t = nextInt
    (1 to t).foreach({
      i =>
        val pwd = next
        var x = 0
        val not: String = pwd.filter(x => ('0' to '9').contains(x))
        if (not.size > 0) {
          x += 1
        }
        val not1: String = pwd.filter(x => ('a' to 'z').contains(x) || ('A' to 'Z').contains(x))
        if (not1.size > 0) {
          x += 1
        }
        val filter: String = pwd.filter(x => "!@#$%^&*()".contains(x))
        if (filter.size > 0) {
          x += 1
        }
        if (pwd.length >= 6) {
          x += 1
        }
        if (pwd.length > 10) {
          x += 1
        }
        val set = new HashSet[Char]()
        pwd.foreach(x => set.add(x))
        if (set.size() == pwd.length) {
          x += 1
        }
        var isCapital = false
        var isSmall = false
        (0 until pwd.length).foreach({
          i =>
            if (('A' to 'Z').contains(pwd.charAt(i))) {
              isCapital = true
            }
            if (('a' to 'z').contains(pwd.charAt(i))) {
              isSmall = true
            }
        })
        if (isCapital && isSmall) {
          x += 1
        }

        out.println("Case #" + i + ":")
        if (x < 4) {
          out.println("weak")
        } else if (x >= 6) {
          out.println("strong")
        } else {
          out.println("normal")
        }
    })
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
