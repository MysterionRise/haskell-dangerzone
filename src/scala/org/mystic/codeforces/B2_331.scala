package scala.org.mystic.codeforces

import java.io.{BufferedOutputStream, InputStreamReader, BufferedReader, PrintWriter}
import java.util.StringTokenizer
import java.lang.{Long, String}

/**
 * @author kperikov
 */
object B2_331 {

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

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def solve = {
    val n = nextInt
    val arr = new Array[Int](n)
    for (i <- 0 until n) {
      arr(i) = nextInt
    }
    val q = nextInt
    for (qq <- 0 until q) {
      val typeOfQuery = nextInt
      val a = nextInt
      val b = nextInt

    }
  }
}
