package org.mystic.hackerrank.algorithms.fp.march2014

import java.io.{BufferedOutputStream, InputStreamReader, BufferedReader, PrintWriter}
import java.util.StringTokenizer
import scala.annotation.tailrec

/**
 * Get 14.54
 */
object CommonDivisors {

  @tailrec
  def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)

  def commonDivisors(a: Int, b: Int) = {
    val g = gcd(a, b)
    println(Stream.from(1).takeWhile(x => x <= g).filter(x => g % x == 0).size)
  }

  val primes: Stream[Int] = 2 #:: Stream.from(3, 2).filter(i => primes.takeWhile(j => j * j <= i).forall(k => i % k > 0))

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

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def solve {
    val testcases = nextInt
    var i = 0
    while (i < testcases) {
      i = i + 1
      commonDivisors(nextInt, nextInt)
    }
  }
}