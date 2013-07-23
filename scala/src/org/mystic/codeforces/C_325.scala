package org.mystic.codeforces

/**
 * Solution for C on MemSQL cup 1
 * @author kperikov
 */
object C_325 {

  def readString = Console.readLine

  def readInts = readString.split(" ").map(_.toInt)

  def main(args: Array[String]): Unit = {
    val Array(m, n) = readInts
    var monstersNum: Array[Int] = Array[Int](m)
    for (i <- 1 to m) {
      val s = readInts
      val monsterType = s(0)
      monstersNum(0) = 0
      for (j <- 0 to s(1) - 1) {
        val prize = s(j + 2)
        println(prize)
      }
    }
  }
}
