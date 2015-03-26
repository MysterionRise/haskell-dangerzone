package org.mystic.codeforces.cf200_300.cf259div2

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
     return 1
   }

   def main(args: Array[String]): Unit = {
     br = new BufferedReader(new InputStreamReader(System.in))
     out = new PrintWriter(new BufferedOutputStream(System.out))
     solve
     out.close
   }
 }