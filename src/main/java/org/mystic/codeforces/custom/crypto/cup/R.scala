package org.mystic.codeforces.custom.crypto.cup

object R {
  def main(args: Array[String]): Unit = {
    readLine().foreach(x => {
      if (x <= 'm') {
        print((x.toInt + 13).toChar)
      } else {
        print((x.toInt - 13).toChar)
      }
    })
  }
}
