package com.atguigu.scala.test01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkPartitionTest {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf()
        conf.setAppName("partitionTest")
        conf.setMaster("local[*]")
        val sc: SparkContext = new SparkContext(conf)
        /**
         * 在parallelize方法上设置分区会按传入的数组长度和分区数进行除法运算
         * 算出每个分区数据的起始位置
         * val start = ((i * length) / numSlices).toInt
         * val end = (((i + 1) * length) / numSlices).toInt
         */

        val dataRDD: RDD[Int] = sc.parallelize(Array(1,2,3,4,5),3)

        dataRDD.saveAsTextFile("output")

        sc.stop()
    }
}
