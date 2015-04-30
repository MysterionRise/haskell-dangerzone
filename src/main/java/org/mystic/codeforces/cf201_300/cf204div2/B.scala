package org.mystic.codeforces.cf201_300.cf204div2

import java.io._
import java.util.StringTokenizer
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import java.util

object B {
  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null
  val mm = new HashMap[Int, HashSet[Int]]
  var ans = new util.TreeMap[Int, String]

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    return st.nextToken
  }

  def nextInt: Int = {
    return Integer.parseInt(next)
  }

  def findAns(v: Int): Unit = {
    val set = mm.get(v).get
    val it = set.toList.sorted.toIterator
    var prev = -1
    var diff = -1
    while (it.hasNext) {
      val nxt = it.next()
      if (prev != -1) {
        if (diff == 0) {
          diff = nxt - prev
          prev = nxt
        } else {
          if (diff != nxt - prev) {
            return
          } else {
            prev = nxt
          }
        }
      } else {
        prev = nxt
        diff = 0
      }
    }
    ans.put(v,(v + " " + diff))
  }

  def solve = {
    val n = nextInt
    val a = new Array[Int](n)
    val set = new HashSet[Int]()
    for (i <- 0 until n) {
      a(i) = nextInt
      set.add(a(i))
      mm.get(a(i)) match {
        case None => {
          val set = new HashSet[Int]()
          set.add(i)
          mm.put(a(i), set)
        }
        case Some(x) => {
          x.add(i)
          mm.put(a(i), x)
        }
      }
    }
    set.foreach(findAns)
    out.println(ans.size)
    val it = ans.keySet().iterator()
    while (it.hasNext) {
      out.println(ans.get(it.next()))
    }
  }


  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}