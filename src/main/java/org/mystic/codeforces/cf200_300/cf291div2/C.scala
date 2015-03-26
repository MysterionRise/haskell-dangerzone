package org.mystic.codeforces.cf200_300.cf291div2

import java.io._
import java.util
import java.util._

object C {

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

  val MODULO = (1e9 + 7).toInt

  def sum(a: Int, b: Int): Int = {
    val sum = a + b
    val res = if (sum < MODULO) sum else sum - MODULO
    return res
  }

  def multiply(a: Int, b: Int): Int = {
    return (1L * a * b % MODULO).toInt
  }

  def calcHash(ch: Array[Char]): Int = {
    var hash = 0
    val len = ch.length
    for (i <- 0 until len) {
      hash = multiply(hash, 31)
      hash = sum(hash, (ch(i) - 'a') + 1)
    }
    return hash
  }

  class HashString(val s: Array[Char], val hash: Int) {

    def this(xs: Array[Char]) {
      this(xs, calcHash(xs))
    }

    override def hashCode(): Int = hash

    override def equals(obj: scala.Any): Boolean = {
      val s1 = obj.asInstanceOf[HashString]
      return Arrays.equals(s, s1.s)
    }
  }

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val pow = new Array[Int](6e5.toInt)
    pow(0) = 1
    for (i <- 1 until pow.length) {
      pow(i) = multiply(pow(i - 1), 31)
    }
    val set = new util.HashSet[HashString]()
    for (i <- 0 until n) {
      set.add(new HashString(next.toCharArray))
    }
    (0 until m).foreach(_ => {
      val t = next.toCharArray
      val hash = calcHash(t)
      var flag = false
      for (i <- 0 until t.length
           if !flag) {
        val prev = t(i)
        for (j <- 'a' to 'z'
             if !flag) {
          if (j != prev) {
            t(i) = j
            val updHash = sum(hash, multiply(sum(MODULO - prev, j), pow(t.length - i - 1)))
            if (set.contains(new HashString(t, updHash))) {
              flag = true
            }
          }
        }
        t(i) = prev
      }
      if (flag) {
        println("YES")
      } else {
        println("NO")
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