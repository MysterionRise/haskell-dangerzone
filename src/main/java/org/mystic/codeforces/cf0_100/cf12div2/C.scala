package org.mystic.codeforces.cf0_100.cf12div2

import java.util
import java.util._
import java.lang._
import java.io._

object C {

  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null
  val ints = new Array[Int](10)

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

  def comp(x: (String, Int), y: (String, Int)): Boolean = {
    x._2 > y._2
  }

  def solve = {
    val n = nextInt
    val m = nextInt
    val fruitPrice = new Array[Int](n)
    for (i <- 0 until n) {
      fruitPrice(i) = nextInt
    }
    val fruits = new util.HashMap[String, Int](m)
    for (i <- 0 until m) {
      val fruit = next
      if (fruits.containsKey(fruit)) {
        val num = fruits.get(fruit)
        fruits.put(fruit, num + 1)
      } else {
        fruits.put(fruit, 1)
      }
    }
    val fruitsWithIndex = fruits.keySet().toArray(new Array[String](0)).map(x => (x, fruits.get(x))).sortWith(comp)
//    fruitsWithIndex.foreach(print)
    val sortedFruitPrice = fruitPrice.sorted
    var ind = 0
    var minPrice = 0
    var maxPrice = 0
    for (i <- 0 until fruitsWithIndex.length) {
      minPrice += fruitsWithIndex(i)._2 * sortedFruitPrice(ind)
      maxPrice += fruitsWithIndex(i)._2 * sortedFruitPrice(sortedFruitPrice.length - ind - 1)
      ind += 1
    }
    out.print(minPrice + " " + maxPrice)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
