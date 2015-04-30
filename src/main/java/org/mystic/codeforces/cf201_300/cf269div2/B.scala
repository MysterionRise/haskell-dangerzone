package org.mystic.codeforces.cf201_300.cf269div2

import java.util._
import java.io._
import java.util

object B {

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

  def comp(a: (Int, Int), b: (Int, Int)): Boolean = {
    if (a._1 == b._1) {
      a._2 < b._2
    } else {
      a._1 < b._1
    }
  }

  def solve: Int = {
    val n = nextInt
    val tasks = new Array[(Int, Int)](n)
    val m = new Array[Int](3000)
    for (i <- 0 until n) {
      val a = nextInt
      tasks(i) = (a, i + 1)
      m(a) += 1
    }
    var could = false
    val list = new util.ArrayList[Int]()
    var x2 = false
    var x2el = -1
    for (i <- 0 until m.length) {
      if (!could) {
        if (m(i) >= 3) {
          could = true
          list.add(i)
        } else if (m(i) == 2 && !x2) {
          x2 = true
          x2el = i
        } else if (m(i) == 2 && x2) {
          could = true
          list.add(i)
          list.add(x2el)
        }
      }
    }
    val sorted = tasks.sortWith(comp)
    if (could) {
      out.println("YES")
      if (list.size() == 1) {
        val el = list.get(0)
        for (i <- 0 until 3) {
          var j = 0
          var flag = false
          while (j < n) {
            if (sorted(j)._1 != el || flag) {
              out.print(sorted(j)._2 + " ")
              j += 1
            } else {
              flag = true
              i match {
                case 0 => {
                  out.print(sorted(j + 0)._2 + " ")
                  out.print(sorted(j + 1)._2 + " ")
                  out.print(sorted(j + 2)._2 + " ")
                }
                case 1 => {
                  out.print(sorted(j + 1)._2 + " ")
                  out.print(sorted(j + 0)._2 + " ")
                  out.print(sorted(j + 2)._2 + " ")
                }
                case 2 => {
                  out.print(sorted(j + 2)._2 + " ")
                  out.print(sorted(j + 0)._2 + " ")
                  out.print(sorted(j + 1)._2 + " ")
                }
                case _ => {}
              }
              j += 3
            }

          }

          out.println()
        }
      } else {
        val el1 = list.get(0)
        val el2 = list.get(1)
        for (i <- 0 until 3) {
          var j = 0
          while (j < n) {
            if (sorted(j)._1 != el1 && sorted(j)._1 != el2) {
              out.print(sorted(j)._2 + " ")
              j += 1
            } else {
              if (sorted(j)._1 == el1) {
                i match {
                  case 0 => {
                    out.print(sorted(j + 0)._2 + " ")
                    out.print(sorted(j + 1)._2 + " ")
                  }
                  case 1 => {
                    out.print(sorted(j + 1)._2 + " ")
                    out.print(sorted(j + 0)._2 + " ")
                  }
                  case 2 => {
                    out.print(sorted(j + 0)._2 + " ")
                    out.print(sorted(j + 1)._2 + " ")
                  }
                  case _ => {}
                }
              } else if (sorted(j)._1 == el2) {
                i match {
                  case 0 => {
                    out.print(sorted(j + 0)._2 + " ")
                    out.print(sorted(j + 1)._2 + " ")
                  }
                  case 1 => {
                    out.print(sorted(j + 0)._2 + " ")
                    out.print(sorted(j + 1)._2 + " ")
                  }
                  case 2 => {
                    out.print(sorted(j + 1)._2 + " ")
                    out.print(sorted(j + 0)._2 + " ")
                  }
                  case _ => {}
                }
              }
              j += 2
            }
          }
          out.println()
        }
      }
    } else {
      out.println("NO")
    }

    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}
