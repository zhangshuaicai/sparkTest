package com.atguigu.scala.test01

object TestMatchCase {
    def main(args: Array[String]): Unit = {
        val a : Int = 10
        val b : Int = 20
        val c : Char = '+'

        val result = c match {
            case '+' => a + b
            case '-' => a - b
            case '*' => a * b
            case '/' => a / b
            case _ => "some thing"
        }
        println(result)
    }
}
