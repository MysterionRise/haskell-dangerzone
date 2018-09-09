package org.mystic.codeforces.cf301_400.cf301div2

import java.io._
import java.util._

import scala.collection.mutable

object C {

  var out: PrintWriter = _
  var br: BufferedReader = _
  var st: StringTokenizer = _

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = Integer.parseInt(next)

  def nextLong: Long = java.lang.Long.parseLong(next)

  def nextDouble: Double = java.lang.Double.parseDouble(next)

  class MultiHashSet[T <% Comparable[T]] {
    val map = new mutable.HashMap[T, Int]()

    def count(x: T): Int = {
      map.getOrElse(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      true
    }
  }

  class MultiTreeSet[T <% Comparable[T]] {
    val map = new TreeMap[T, Int]()

    def count(x: T): Int = {
      map.getOrDefault(x, 0)
    }

    def add(x: T): Unit = map.put(x, count(x) + 1)

    def first(): T = map.firstKey()

    def last(): T = map.lastKey()

    def remove(x: T): Boolean = {
      val prev = count(x)
      if (prev == 0)
        return false
      if (prev == 1) {
        map.remove(x)
      } else {
        map.put(x, prev - 1)
      }
      true
    }
  }

  def solve: Int = {
    val n = nextInt
    val m = nextInt
    val g = new Array[Array[Int]](n + 2)
    val d = new Array[Array[Int]](n + 2)
    for (i <- g.indices) {
      g(i) = new Array[Int](m + 2)
      d(i) = new Array[Int](m + 2)
    }
    for (i <- 1 to n) {
      val in = next.toCharArray
      for (j <- 1 to m) {
        if (in(j - 1) == '.') {
          g(i)(j) = 1
        }
      }
    }
    val r1 = nextInt
    val c1 = nextInt
    val r2 = nextInt
    val c2 = nextInt
    val dx = Array(-1, 1, 0, 0)
    val dy = Array(0, 0, -1, 1)
    if (r1 == r2 && c1 == c2) {
      for (i <- 0 until 4) {
        val x1 = r1 + dx(i)
        val y1 = c1 + dy(i)
        if (g(x1)(y1) == 1) {
          out.println("YES")
          return 1
        }
      }
    } else if ((r1 == r2 && Math.abs(c1 - c2) == 1) || (c1 == c2 && Math.abs(r1 - r2) == 1)) {
      if (g(r2)(c2) == 0) {
        out.println("YES")
        return 1
      } else {
        var cnt = 0
        for (i <- 0 until 4) {
          val x1 = r2 + dx(i)
          val y1 = c2 + dy(i)
          if (g(x1)(y1) == 1) {
            cnt += 1
          }
        }
        if (cnt >= 1) {
          out.println("YES")
          return 1
        }
      }
    } else {
      val q = new mutable.Queue[(Int, Int)]()
      q.enqueue((r1, c1))

      while (q.nonEmpty) {
        val x = q.dequeue
        for (i <- 0 until 4) {
          val x1 = x._1 + dx(i)
          val y1 = x._2 + dy(i)
          if (x1 == r2 && y1 == c2) {
            if (g(r2)(c2) == 0) {
              out.println("YES")
              return 1
            } else {
              var cnt = 0
              for (i <- 0 until 4) {
                val x1 = r2 + dx(i)
                val y1 = c2 + dy(i)
                if (g(x1)(y1) == 1) {
                  cnt += 1
                }
              }
              if (cnt >= 2) {
                out.println("YES")
                return 1
              }
            }
          } else if (g(x1)(y1) == 1 && d(x1)(y1) == 0) {
            q.enqueue((x1, y1))
            d(x1)(y1) = d(x._1)(x._2) + 1
          }
        }
      }
    }
    out.println("NO")
    0
  }
}