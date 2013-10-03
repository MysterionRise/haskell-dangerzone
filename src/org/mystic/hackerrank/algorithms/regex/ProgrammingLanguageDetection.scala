package org.mystic.hackerrank.algorithms.regex

import scala.io._

object ProgrammingLanguageDetection {

  var language = ""

  def checkLanguage(x: String): Unit = {
    if (x.contains("#include")) {
      if (language == "") {
        language = "C"
      }
    } else if (x.contains("import")) {
      if (language == "") {
        language = "Java"
      }
    }
  }

  def main(args: Array[String]) {
    Source.stdin.getLines().foreach(checkLanguage)
    if (language == "") {
      println("Python")
    } else {
      println(language)
    }
  }
}