package org.mystic.codeforces.cf0_100.cf21

import java.util._
import java.io._

object A {

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
    val user = next
    if (user.split("@").length != 2 ||  user.endsWith("@")) {
      out.println("NO")
      return 1
    }
    val name = user.split("@")(0)
    if (name.length <= 0 || name.length > 16) {
      out.println("NO")
      return 1
    }
    val part2 = user.split("@")(1)
    val allow = "QWERTYUIOPASDFGHJKLZXCVBNMabcdefghijklmnopqrstuvwxyz1234567890_".toCharArray
    if (name.filter(x => !allow.contains(x)).length > 0) {
      out.println("NO")
      return 1
    }
    if (part2.split("/")(0).length <= 0 || part2.split("/")(0).length > 32) {
      out.println("NO")
      return 1
    }

    val hosts = part2.split("/")(0).split("\\.")
    if (part2.split("/")(0).startsWith(".") || part2.split("/")(0).endsWith(".")) {
      out.println("NO")
      return 1
    }
    if (part2.split("/")(0).contains(".") && hosts.length == 0) {
      out.println("NO")
      return 1
    }
    for (i <- 0 until hosts.length) {
      if (hosts(i).filter(x => !allow.contains(x)).length > 0) {
        out.println("NO")
        return 1
      }
      if (hosts(i).length <= 0 || hosts(i).length > 16) {
        out.println("NO")
        return 1
      }
    }

    if (part2.split("/").length > 1) {
      val res = part2.split("/")(1)
      if (res.filter(x => !allow.contains(x)).length > 0) {
        out.println("NO")
        return 1
      }
    }
    if (user.endsWith("/")) {
      out.println("NO")
      return 1
    }
    out.println("YES")
    return 1
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }
}