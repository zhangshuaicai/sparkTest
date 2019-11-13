package com.atguigu.scala.test01

object TestException {
    def main(args: Array[String]): Unit = {
        try {
            var n = 10/0
        }catch {
            //scala中异常不会按范围的大小，分前后
            case ex: ArithmeticException=>{
                println("发生算术异常")
            }
            case ex : Exception=>{
                println("发生异常")
            }
        }finally {
            println("finally")
        }
    }
}
