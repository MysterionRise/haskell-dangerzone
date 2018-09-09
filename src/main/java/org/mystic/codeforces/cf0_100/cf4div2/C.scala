package org.mystic.codeforces.cf0_100.cf4div2

import java.util
import java.util._
import java.lang._
import java.io._

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

  def nextInt: Int = {
    Integer.parseInt(next)
  }

  def nextLong: Long = {
    Long.parseLong(next)
  }

  def solve = {
    val n = nextInt
    val names = new util.HashSet[String]()
    val namedInd = new util.HashMap[String, Int]()
    for (i <- 0 until n) {
      val name = next
      if (names.contains(name)) {
        val ind = namedInd.get(name)
        out.println(name + ind.toString)
        names.add(name + ind.toString)
        namedInd.remove(name)
        namedInd.put(name, ind + 1)
      } else {
        names.add(name)
        namedInd.put(name, 1)
        out.println("OK")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
