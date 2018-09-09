package org.mystic.ir.bayan.qual2014

import java.io._
import java.util._
import java.util

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

  def shift(s: Array[Int], ind: Int): Array[Int] = {
    var cnt = 0
    val res = new Array[Int](4)
    var i = (s.length - ind) % s.length
    while (cnt < s.length) {
      res(cnt) = s(i)
      i = (i + 1) % s.length
      cnt += 1
    }
    res
  }

  def rotate(sh: Array[Int], arr: Array[Int], num: Int): Array[Int] = {
    val s = new Array[Int](4)
    for (i <- 0 until 4) {
      s(i) = arr(sh(i))
    }
    val shifted = shift(s, num)
    for (i <- 0 until 4) {
      arr(sh(i)) = shifted(i)
    }
    arr
  }

  def solve: Int = {
    val map = new util.HashMap[String, Array[Int]]()
    map.put("X", Array(0, 1, 5, 2))
    map.put("Y", Array(1, 4, 2, 3))
    map.put("Z", Array(0, 4, 5, 3))

    (0 until nextInt).foreach(_ => {
      val sides = new Array[String](6)
      //            F  T  B  L  R  Rear
      var f = Array(0, 1, 2, 3, 4, 5)
      (0 until 6).foreach(i => sides(i) = next)
            (0 until nextInt).foreach(_ => {
              f = rotate(map.get(next), f, nextInt % 4)
            })
      (0 until 6).foreach(i => out.print(sides(f(i)) + " "))
      out.println()
    })
    1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
