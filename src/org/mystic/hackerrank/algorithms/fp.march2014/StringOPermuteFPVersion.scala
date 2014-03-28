package org.mystic.hackerrank.algorithms.fp

import scala.io.Source
import scala.annotation.tailrec

/**
 * This super FP version get's only 13.33 due to TL
 */
object StringOPermuteFPVersion {

  @tailrec
  def swap(x: String): Unit = {
    x.size match {
      case 0 => println
      case _ => {
        val p = x.splitAt(2)
        print(p._1.reverse)
        swap(p._2)
      }
    }
  }

  def main(args: Array[String]) {
    Source.stdin.getLines().drop(1).foreach(swap)
  }
}
