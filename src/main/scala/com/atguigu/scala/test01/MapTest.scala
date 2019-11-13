package com.atguigu.scala.test01

import scala.collection.mutable

object MapTest {
    def main(args: Array[String]): Unit = {
        val map1: mutable.Map[String, Int] = mutable.Map(("a", 1), ("b", 1), ("c", 1))
        val map2: mutable.Map[String, Int] = mutable.Map(("a", 1), ("d", 1), ("c", 1))

        val result: mutable.Map[String, Int] = map1.foldLeft(map2)((map, kv) => {
            var k = kv._1
            var v = kv._2
            map(k) = map.getOrElse(k, 0) + v
            map
        })
        println(result)
        val list = List(1,2,3)
//        list.groupBy()
    }
}
