package org.mystic.codeforces

import collection.mutable.HashMap
import java.util._

/**
 * @author kperikov
 */
object A_331 {

  def readString = Console.readLine

  def readInts = readString.split(" ").map(_.toInt)

  def main(args: Array[String]): Unit = {
    val n = readInt
    val beauty = readInts
    val map = new HashMap[Int, ArrayList[Int]]
    for (i <- 0 to n - 1) {
      val some = map.get(beauty(i))
      some match {
        case Some(x) => {
          x add i
          map put(beauty(i), x)
        }
        case None =>
      }
    }
  }
}
