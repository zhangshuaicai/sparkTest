package com.atguigu.scala.test01

/**
 * 偏函数
 */
object TestCollect {
    def main(args: Array[String]): Unit = {
        //将该List(1,2,3,4,5,6,"test")中的Int类型的元素加一，并去掉字符串。
        val list: List[Any] = List(1,2,3,4,5,6,"test")
        val list1 = list.collect{case x : Int=> x + 1}
        println(list1)
        val list2 = list.filter(x => x.isInstanceOf[Int]).map(x => x.asInstanceOf[Int] + 1)
        println(list2)
    }
}
