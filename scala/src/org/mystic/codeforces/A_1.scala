package org.mystic.codeforces

import scala.math._
/**
 * @author kperikov
 **/

object A_1 {

  def main(args: Array[String]): Unit = {
    val Array(n, m, a) = readLine().split(" ").map(_.toLong)
    println((ceil(n.toDouble / a.toDouble) * ceil(m.toDouble / a.toDouble)).toLong)
  }
}
