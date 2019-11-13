package com.atguigu.scala.test01

/**
 * 匹配列表
 */
object TestMatchList {
    def main(args: Array[String]): Unit = {
        //list是一个存放List集合的数组
        //请思考，如果要匹配 List(88) 这样的只含有一个元素的列表,并原值返回.应该怎么写
        for (list <- Array(List(0), List(1, 0), List(0, 0, 0), List(1, 0, 0), List(88))) {

            val result = list match {

                case List(0) => "0" //匹配List(0)
                case List(x, y) => x + "," + y //匹配有两个元素的List
                case List(0, _*) => "0 ..."
                case _ => "something else"
            }

            println(result)
        }

        val list: List[Int] = List(1,2,3,4,5)
        list match {
            case first :: second :: rest => println(first+"-"+second+"-"+rest)
            case _ => println("soemthing")
        }

    }
}
