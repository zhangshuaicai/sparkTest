package com.atguigu.scala.test02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkOperator01 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf()
        conf.setMaster("local[*]")
        conf.setAppName("Spark Creation")
        val sc: SparkContext = new SparkContext(conf)

        val arr: Array[Int] = Array(1,2,3,4,5)

        //val arrRDD: RDD[Int] = sc.parallelize(arr)
        //makeRDD 等同于 parallelize，底层makeRDD调用parallelize方法
        val arrRDD: RDD[Int] = sc.makeRDD(arr)

        println(arrRDD.collect().mkString(","))

        sc.stop()
    }
}
