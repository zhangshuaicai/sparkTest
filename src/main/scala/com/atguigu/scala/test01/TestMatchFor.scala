package com.atguigu.scala.test01

/**
 * for表达式中的模式匹配
 */
object TestMatchFor {
    def main(args: Array[String]): Unit = {
        val map = Map("a"->1,"b"->0,"c"->3)
        for ((k,v) <- map) {
            println(k + "-" + v)
        }
        println("------------------------")
        //过滤值不为0的数据
        for ((k,0) <- map) {
            println(k + "-" + 0)
        }
        println("------------------------")
        //过滤掉值为0的数据
        for ((k,v) <- map if v > 0) {
            println(k + "-" + v)
        }
    }
}
