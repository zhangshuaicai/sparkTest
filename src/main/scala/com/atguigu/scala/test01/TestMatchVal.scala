package com.atguigu.scala.test01

/**
 * 匹配常量 ,可以匹配所有的字面量，包括字符串，字符，数字，布尔值等等
 */
object TestMatchVal {
    def main(args: Array[String]): Unit = {
        println(desc("asss"))
        println(desc(1))
        println(desc(true))
        println(desc('z'))
        println(desc())
    }
    def desc(x : Any) = x match {
        case s : String => "String"
        case i : Int => "Int"
        case b : Boolean => "Boolean"
        case c : Char => "Char"
        case _ => "something"
    }
}
