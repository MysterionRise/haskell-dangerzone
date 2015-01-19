package org.mystic.crypto.cup

object J {
  def main(args: Array[String]): Unit = {
    readLine().foreach(decrypt)
  }

  val map = Map(
    'a' -> 'n',
    'b' -> 'h',
    'c' -> 'r',
    'd' -> 'x',
    'e' -> 'k',
    'f' -> 'e',
    'g' -> 'y',
    'h' -> 'o',
    'i' -> 'q',
    'j' -> 'm',
    'k' -> 'j',
    'l' -> 'b',
    'm' -> 'd',
    'n' -> 'u',
    'o' -> 'v',
    'p' -> 'a',
    'q' -> 'p',
    'r' -> 'w',
    's' -> 'g',
    't' -> 'z',
    'u' -> 'f',
    'v' -> 'i',
    'w' -> 'c',
    'x' -> 's',
    'y' -> 't',
    'z' -> 'l'
  )

  def decrypt(ch: Char) = {
    System.out.print((map get ch).get)
  }
}
