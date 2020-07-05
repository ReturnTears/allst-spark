package com.allst.ca.demo

/**
  * 匹配(match)表达式
  * @author YiYa
  * @since 2020-07-05 下午 11:18
  */
object MatchEx {
    def main(args: Array[String]): Unit = {
        val firstArg = if (args.length > 0) args(0) else ""
        val friend =
            firstArg match {
                case "salt" =>
                    println("pepper")
                    "PEPPER"
                case "chips" =>
                    println("salsa")
                    "SALSA"
                case "eggs" =>
                    println("bacon")
                    "BACON"
                case _ => println("huh?")
            }
        println(friend)
    }
}
