package org.mystic.codeforces.cf201_300.cf259div2

import java.util._
import java.io._

object A {

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

   def solve: Int = {
     val n = nextInt
     var pos = n / 2
     var len = 1
     for (i <- 0 until n) {
       for (j <- 0 until n) {
         if (j >= pos && j < pos + len) {
           out.print("D")
         } else {
           out.print("*")
         }
       }
       out.println
       if (i < n / 2) {
         pos -= 1
         len += 2
       } else {
         pos += 1
         len -= 2
       }
     }
     1
   }

   def main(args: Array[String]): Unit = {
     br = new BufferedReader(new InputStreamReader(System.in))
     out = new PrintWriter(new BufferedOutputStream(System.out))
     solve
     out.close
   }
 }