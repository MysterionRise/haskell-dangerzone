package org.mystic.crypto.cup

object I {
  def main(args: Array[String]): Unit = {
    readLine().grouped(8).toList.foreach(x => {
      var ch = 0
      (0 until 8).foreach(i => {
        ch = ch | ((x(7 - i) - 48) << i)
      })
      System.out.print(ch.toChar)
    })
  }
}
