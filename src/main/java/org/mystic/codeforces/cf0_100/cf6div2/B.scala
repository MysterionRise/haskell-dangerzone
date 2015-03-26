package org.mystic.codeforces.cf0_100.cf6div2

import java.util
import java.util._
import java.lang._
import java.io._

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
    return Long.parseLong(next)
  }

  def solve = {
    val n = nextInt
    val m = nextInt
    val presidentColor = next.toCharArray()(0)
    val cabinet = new Array[Array[Char]](n + 2)
    for (i <- 0 until n + 2) {
      cabinet(i) = new Array[Char](m + 2)
      Arrays.fill(cabinet(i), '.')
    }
    for (i <- 1 to n) {
      val str = next.toCharArray
      for (j <- 1 to m) {
        cabinet(i)(j) = str(j - 1)
      }
    }
    var ans = 0
    val colors = new util.HashSet[Char]()
    val dx = Array(-1, 1, 0, 0)
    val dy = Array(0, 0, -1, 1)
    for (i <- 1 to n) {
      for (j <- 1 to m) {
        if (cabinet(i)(j) == presidentColor) {
          for (k <- 0 until 4) {
            val testedColor = cabinet(i + dx(k))(j + dy(k))
            if (testedColor != presidentColor && testedColor != '.' && !colors.contains(testedColor)) {
              ans += 1
              colors.add(testedColor)
            }
          }
        }
      }
    }
    out.println(ans)

  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
