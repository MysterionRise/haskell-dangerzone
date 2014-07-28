package org.mystic.jetbrains.challenge.alpha

import java.util.Scanner
import java.io.PrintWriter

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val str = sc.next()
    var sum1 = 0
    var sum2 = 0
    var i = 0
    while(i < 3) {
        sum1 += (str.charAt(i) - '0')
        i++
    }
    while(i < 6) {
        sum2 += (str.charAt(i) - '0')
        i++
    }
    sc.close()
    val out = PrintWriter(System.out)
    out.println(if (sum1 == sum2)"Lucky ticket" else "Unlucky ticket")
    out.close()
}


