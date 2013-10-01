package org.mystic.codeforces.gym100229

import java.io._
import java.util.StringTokenizer

object F {
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
    val string = next
    val n = string.length
    var result = 0
    for (i <- 1 to n) {
      val prefMax = prefixFunc(string.substring(0, i).reverse).toList.max
      result += string.substring(0, i).length - prefMax
    }
    out.println(result)
  }

  def prefixFunc(string: String): Array[Int] = {
    val π = new Array[Int](string.length)
    π(0) = 0
    for (i <- 1 to string.length - 1) {
      var j = π(i - 1)
      while (j != 0 && string(j) != string(i)) {
        j = π(j - 1)
      }
      if (string(j) == string(i)) {
        π(i) = j + 1
      } else {
        π(i) = 0
      }
    }
    π
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(new FileInputStream("bacon.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("bacon.out")))
    solve
    out.close
  }
}