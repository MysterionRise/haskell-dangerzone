package scala.org.mystic.codeforces

import java.io.{BufferedOutputStream, InputStreamReader, BufferedReader, PrintWriter}
import java.util.StringTokenizer
import java.lang.Long
import scala.collection.immutable.HashSet

/**
 * @author kperikov
 */
object D_334 {
  var out: PrintWriter = null
  var br: BufferedReader = null
  var st: StringTokenizer = null

  def next: String = {
    while (st == null || !st.hasMoreTokens) {
      st = new StringTokenizer(br.readLine)
    }
    st.nextToken
  }

  def nextInt: Int = {
    Integer.parseInt(next)
  }

  def nextLong: Long = {
    Long.parseLong(next)
  }

  def main(args: Array[String]): Unit = {
    br = new BufferedReader(new InputStreamReader(System.in))
    out = new PrintWriter(new BufferedOutputStream(System.out))
    solve
    out.close
  }

  def solve = {
    val n = nextInt
    val m = nextInt
    var setX = new HashSet[Int]()
    var setY = new HashSet[Int]()
    for (i <- 0 until m) {
      setX += nextInt
      setY += nextInt
    }
    var ans = 0
    for (i <- 2 to n - 1) {
      if (!setX.contains(i)) {
        ans += 1
      }
      if (!setY.contains(i)) {
        ans += 1
      }
    }
    if (n % 2 == 1 && !setX.contains(n / 2) && !setY.contains(n / 2) && ans > 1) {
      ans -= 1
    }
    out.println(ans)
  }
}
