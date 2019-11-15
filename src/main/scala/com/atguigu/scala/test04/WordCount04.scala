package com.atguigu.scala.test04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount04 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(conf)

        val rdd = sc.parallelize(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
        //方式四：combineByKey
        val value: RDD[(String, (Int, Int))] = rdd.combineByKey(
            (x: Int) => (x, 1),
            (x: (Int, Int), y: Int) => (x._1 + y, x._2 + 1),
            (x: (Int, Int), y: (Int, Int)) => (x._1 + y._1, x._2 + y._2)
        )

        println(value.collect().mkString(","))

        sc.stop()
    }
}
