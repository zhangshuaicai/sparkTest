package com.atguigu.scala.test01

object TestImplicitFunction {
    def main(args: Array[String]): Unit = {
        val i : Int = 2.0
        println(i)
        println(2.myMax(6))
        hello//隐式转换，必须去掉小括号
        hello()
    }
    //隐式函数，将double类型的数值转换成int
    implicit def transform(d : Double):Int={
        d.toInt
    }
    //隐式函数，将Int值转换成对象
    implicit def IntToMyRichInt(i : Int) : MyRichInt={
        new MyRichInt(i)
    }
    implicit val ss : String = "aaaa aaaa"
    def hello(implicit s :String="good boo"):Unit={
        println(s)
    }
}
class MyRichInt(val self: Int) {
    def myMax(i: Int): Int = {
        if (self < i) i else self
    }

    def myMin(i: Int): Int = {
        if (self < i) self else i
    }
}
