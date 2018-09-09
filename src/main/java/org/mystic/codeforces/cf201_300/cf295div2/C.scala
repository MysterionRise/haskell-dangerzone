package org.mystic.codeforces.cf201_300.cf295div2
import java.io._
import java.util.StringTokenizer

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
    java.lang.Long.parseLong(next)
  }

  val MOD = 1e9 + 7

  def solve: Int = {
    val n = nextInt
    val ch = next.toCharArray
    val cnt = new Array[Int](4)
    for (i <- 0 until n) {
      ch(i) match {
        case 'A' => cnt(0) += 1
        case 'C' => cnt(1) += 1
        case 'G' => cnt(2) += 1
        case 'T' => cnt(3) += 1
        case _ =>
      }
    }
    val max = cnt.toList.max
    var k = 0
    for (i <- 0 until 4) {
      if (cnt(i) == max) {
        k += 1
      }
    }
    out.println(BigInt.int2bigInt(k).pow(n).%(BigInt.int2bigInt(MOD.toInt)))
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}