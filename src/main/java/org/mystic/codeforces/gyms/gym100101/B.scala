package org.mystic.codeforces.gyms.gym100101

import java.util._
import java.io._

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

  def solve: Int = {
    val n = nextInt
    val map = new TreeMap[Int, Int]()
    for (i <- 0 until n) {
      val cmd = next
      if (cmd == "GetMin") {
        out.println(map.firstKey())
        val cnt = map.get(map.firstKey)
        if (cnt > 1) {
          map.put(map.firstKey, cnt - 1)
        } else {
          map.remove(map.firstKey)
        }
      } else if (cmd == "GetMax") {
        out.println(map.lastKey)
        val cnt = map.get(map.lastKey)
        if (cnt > 1) {
          map.put(map.lastKey, cnt - 1)
        } else {
          map.remove(map.lastKey)
        }
      } else {
        val value = Integer.parseInt(cmd.substring("Insert(".length, cmd.length - 1))
        val cnt = map.get(value)
        if (null == cnt) {
          map.put(value, 1)
        } else {
          map.put(value, cnt + 1)
        }
      }
    }
    return 1
  }

  def main(args: Array[String]): Unit = {
    //    br = new BufferedReader(new InputStreamReader(System.in))
    //    out = new PrintWriter(new BufferedOutputStream(System.out))
    br = new BufferedReader(new InputStreamReader(new FileInputStream("minmax.in")))
    out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("minmax.out")))
    solve
    out.close
  }
}
