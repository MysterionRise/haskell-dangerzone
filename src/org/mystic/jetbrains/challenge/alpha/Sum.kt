package org.mystic.jetbrains.challenge.alpha

import java.util.Scanner
import java.io.PrintWriter

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val a = sc.nextInt()
    val b = sc.nextInt()
    sc.close()
    val out = PrintWriter(System.out)
    out.println(a + b)
    out.close()
}
