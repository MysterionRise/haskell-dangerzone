package org.mystic.crypto.cup

object C {
  def main(args: Array[String]): Unit = {
    var prev = 'a'
    readLine().foreach(x => {
      val diff = x - prev
      diff match {
        case n if n >= 0 => print(('a' + diff).toChar)
        case _ => print(('z' + diff + 1).toChar)
      }
      prev = x
    })
  }
}
