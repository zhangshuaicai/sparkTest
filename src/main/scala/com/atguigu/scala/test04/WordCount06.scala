package com.atguigu.scala.test04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount06 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(conf)

        val rdd = sc.parallelize(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
        //方式六：foldByKey
        val value: RDD[(String, Int)] = rdd.foldByKey(0)(_ + _)

        println(value.collect().mkString(","))

        sc.stop()
    }
}
