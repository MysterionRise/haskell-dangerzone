package org.mystic.codeforces.cf0_100.cf18div2

import java.math.BigInteger
import java.util
import java.util._
import java.io._

object D {

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

  def noUsedFromTo(from: Int, to: Int, used: Array[Boolean]): Boolean = {
    var flag = true
    for (i <- from to to) {
      flag &= !used(i)
    }
    flag
  }

  def solve: Int = {
    val n = nextInt
    val events = new Array[(Boolean, Int, Int)](n)
    val needToSell = new util.TreeSet[Int]()
    val used = new Array[Boolean](n)
    for (i <- 0 until n) {
      if (next == "win") {
        events(i) = (true, nextInt, i)
      } else {
        events(i) = (false, nextInt, i)
        needToSell.add(events(i)._2)
      }
    }
    var ans: BigInteger = BigInteger.ZERO
    val it = needToSell.descendingIterator
    while (it.hasNext) {
      val price = it.next
      val pos = events.find(x => !x._1 && x._2 == price).get._3
      var i = pos - 1
      while (i >= 0) {
        if (events(i)._1 && events(i)._2 == price && noUsedFromTo(i, pos, used)) {
          ans = ans.add(BigInteger.valueOf(2).pow(price))
          for (j <- i to pos) {
            used(j) = true
          }
          i = -1
        }
        i -= 1
      }
    }
    out.println(ans)
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
