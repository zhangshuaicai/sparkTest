package com.atguigu.scala.test01

import com.atguigu.scala.test01.TestTransform.Teacher

object TestTransform extends PersonTrait {
    def main(args: Array[String]): Unit = {
        //（1）首先会在当前代码作用域下查找隐式实体
        val teacher = new Teacher()
        teacher.eat()
        teacher.say()
    }
    class Teacher {
        def eat(): Unit = {
            println("eat...")
        }
    }

}
trait PersonTrait {

}
object PersonTrait {
    // 隐式类 : 类型1 => 类型2
    implicit class Person5(user:Teacher) {

        def say(): Unit = {
            println("say...")
        }
    }
}
