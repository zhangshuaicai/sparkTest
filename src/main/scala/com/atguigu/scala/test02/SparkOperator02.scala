package com.atguigu.scala.test02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkOperator02 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf()
        conf.setMaster("local[*]")
        conf.setAppName("Spark Creation")
        val sc: SparkContext = new SparkContext(conf)

        val textFileRDD: RDD[String] = sc.textFile("input/word.txt")
        println(textFileRDD.collect().mkString(","))


        sc.stop()
    }
}
