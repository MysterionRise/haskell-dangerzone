package org.mystic.hackerrank.algorithms.fp

import scala.annotation.tailrec

object ListLength {
  def f(arr: List[Int]): Int = {
    countSize(1, arr)
  }

  val s = Stream.from(1).takeWhile(x => x < n)

  @tailrec
  def countSize(acc: Int, list: List[Int]): Int = {
    list.tail match {
      case Nil => acc
      case _ => countSize(acc + 1, list.tail)
    }
  }
}
