package org.mystic.ir.bayan.qual2014

import java.io._
import java.util._

// WA
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

  def foo(): Map[String, Int] = {
    return new HashMap[String, Int]
  }

  def shift(s: String, ind: Int): String = {
    var cnt = 0
    var i = ind
    val sb = new StringBuilder
    while (cnt < s.length) {
      sb.append(s.charAt(i))
      i = (i + 1) % s.length
      cnt += 1
    }
    return sb.toString()
  }

  def rotateX(ss: Array[Array[Char]], n: Int): Array[Array[Char]] = {
    val s = new StringBuilder
    for (i <- 0 until 4) {
      s.append(ss(i)(1))
    }
    val shifted = shift(s.toString(), n).toCharArray
    for (i <- 0 until 4) {
      ss(i)(1) = shifted(i)
    }
    return ss
  }

  def rotateY(ss: Array[Array[Char]], n: Int): Array[Array[Char]] = {
    val s = new StringBuilder
    s.append(ss(1)(1))
    s.append(ss(2)(2))
    s.append(ss(3)(1))
    s.append(ss(2)(0))
    val shifted = shift(s.toString(), n).toCharArray
    ss(1)(1) = shifted(0)
    ss(2)(2) = shifted(1)
    ss(3)(1) = shifted(2)
    ss(2)(0) = shifted(3)
    return ss
  }

  def rotateZ(ss: Array[Array[Char]], n: Int): Array[Array[Char]] = {
    val s = new StringBuilder
    s.append(ss(2)(0))
    s.append(ss(2)(1))
    s.append(ss(2)(2))
    s.append(ss(0)(1))
    val shifted = shift(s.toString(), n).toCharArray
    ss(2)(0) = shifted(0)
    ss(2)(1) = shifted(1)
    ss(2)(2) = shifted(2)
    ss(0)(1) = shifted(3)
    return ss
  }

  def solve: Int = {
    (0 until nextInt).foreach(_ => {
      val sides = new Array[String](6)
      var f = new Array[Array[Char]](4)
      f(0) = "060".toCharArray
      f(1) = "020".toCharArray
      f(2) = "415".toCharArray
      f(3) = "030".toCharArray
      (0 until 6).foreach(i => sides(i) = next)
      (0 until nextInt).foreach(_ => {
        next match {
          case "X" => nextInt % 4 match {
            case 1 => f = rotateX(f, 1)
            case 2 => f = rotateX(f, 2)
            case 3 => f = rotateX(f, 3)
            case _ =>
          }
          case "Y" => nextInt % 4 match {
            case 1 => f = rotateY(f, 1)
            case 2 => f = rotateY(f, 2)
            case 3 => f = rotateY(f, 3)
            case _ =>
          }
          case "Z" => nextInt % 4 match {
            case 1 => f = rotateZ(f, 1)
            case 2 => f = rotateZ(f, 2)
            case 3 => f = rotateZ(f, 3)
            case _ =>
          }
        }
      })
      out.print(sides(f(2)(1) - 49) + " ")
      out.print(sides(f(1)(1) - 49) + " ")
      out.print(sides(f(3)(1) - 49) + " ")
      out.print(sides(f(2)(0) - 49) + " ")
      out.print(sides(f(2)(2) - 49) + " ")
      out.print(sides(f(0)(1) - 49) + " ")
      out.println
    })
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
//    br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/kperikov/16.in")))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
