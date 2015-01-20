package org.mystic.crypto.cup

object Q {
  def main(args: Array[String]): Unit = {
    readLine().foreach(x => {
      if (x == 'z') {
        print('a')
      } else
        print((x.toInt + 1).toChar)
    })
  }
}
