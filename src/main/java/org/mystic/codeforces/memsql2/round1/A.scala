package org.mystic.codeforces.memsql2.round1

import java.util._
import java.lang._
import java.io._

object A {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null
  val pokemons: Array[String] = Array("vaporeon", "jolteon", "flareon", "espeon", "umbreon", "leafeon", "glaceon", "sylveon")

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
    val sz = nextInt
    val in = next
    var num = 0

    for (e <- pokemons) {
      num = 0
      if (e.length == sz) {
        for (i <- 0 until sz) {
          if (e.charAt(i) == in.charAt(i) || in.charAt(i) == '.') {
            num = num + 1
          }
        }
      }
      if (num == sz) {
        out.println(e)
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
