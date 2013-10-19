package org.mystic.jetbrains.challenge.alpha

import java.util.Scanner
import java.io.PrintWriter

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    sc.close()
    var ans = 0
    for (x in 1..n)
        for (y in x..n) {
            if (x + y == n && isPrime(x) && isPrime(y)) {
                ans++
            }
        }
    val out = PrintWriter(System.out)
    out.println(ans)
    out.close()
}

fun isPrime(n: Int): Boolean {
    if (n == 0 || n == 1)
        return false
    if (n == 2 || n == 3)
        return true
    for (x in 2..Math.sqrt(n.toDouble())) {
        if (n % x == 0.0) {
            return false
        }
    }
    return true
}