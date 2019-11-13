package com.atguigu.scala.test01

object TestImplicitClass {
    //scala 2.10后，提供了隐式类
    implicit class MyRichInt(arg : Int){
        def myMax(i: Int): Int = {
            if (arg < i) i else arg
        }

        def myMin(i: Int) = {
            if (arg < i) arg else i
        }

    }
    def main(args: Array[String]): Unit = {
        println(1.myMax(3))
    }
}
