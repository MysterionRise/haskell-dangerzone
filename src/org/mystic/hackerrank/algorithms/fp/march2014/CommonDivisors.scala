package org.mystic.hackerrank.algorithms.fp.march2014

import java.io.{BufferedOutputStream, InputStreamReader, BufferedReader, PrintWriter}
import java.util.StringTokenizer
import scala.annotation.tailrec

/**
 * Full solution
 */
object CommonDivisors {

  @tailrec
  def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)

  def getResult(num: Int, acc: Int, accMul: Int, stream: List[Int]): Int = {
    stream.size match {
      case 0 => acc
      case _ => {
        num % stream.head match {
          case 0 => getResult(num / stream.head, acc, accMul + 1, stream)
          case _ => getResult(num, acc * accMul, 1, stream.tail)
        }
      }
    }
  }

  def commonDivisors(a: Int, b: Int) = {
    val g = gcd(a, b)
    println(getResult(g, 1, 1, primes.takeWhile(i => i <= 10 * Math.sqrt(g)).toList)) // tricky to go until 10 times of Math.sqrt of GCD
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