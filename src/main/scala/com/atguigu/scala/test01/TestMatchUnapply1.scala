package com.atguigu.scala.test01

//case class 样例类 会自动创建apply 和 unapply 方法
case class User(name: String, age: Int)

object TestMatchUnapply1 {
    def main(args: Array[String]): Unit = {
        val user: User = User("zhangsan",1)
        val result = user match {
            case User("zhangsan", 1) => "yes"
            case _ => "no"
        }
        println(result)
    }
}
