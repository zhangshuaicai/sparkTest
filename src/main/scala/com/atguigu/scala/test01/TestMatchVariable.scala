package com.atguigu.scala.test01

/**
 * 变量声明中的模式匹配
 */
object TestMatchVariable {
    def main(args: Array[String]): Unit = {
        val (x, y) = (1, 2)
        println(s"x=$x,y=$y")

        val Array(first,second, _*) = Array(1,7,2,9)
        println(s"first=$first,second=$second")

        val Person(name,age) = Person("zhangsan",1)
        println(s"name=$name,age=$age")
    }
}

case class Person(name:String,age:Int)
