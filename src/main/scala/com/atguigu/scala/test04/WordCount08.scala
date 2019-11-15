package com.atguigu.scala.test04

import org.apache.spark.{SparkConf, SparkContext}

object WordCount08 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(conf)

        val rdd = sc.parallelize(List(("a", 1), ("a", 1), ("c", 2), ("b", 3), ("c", 2), ("c", 2)), 2)

        //方式八：countByValue
        val value: collection.Map[(String, Int), Long] = rdd.countByValue()

        println(value)

        sc.stop()
    }
}
