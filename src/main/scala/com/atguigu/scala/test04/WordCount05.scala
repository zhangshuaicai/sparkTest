package com.atguigu.scala.test04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount05 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(conf)

        val listRDD: RDD[(String, Int)] = sc.parallelize(List(("female",1),("male",5),("female",5),("male",2)))
        //方式五：groupByKey
        val groupRDD: RDD[(String, Iterable[Int])] = listRDD.groupByKey()
        val value1: RDD[Any] = groupRDD.map(t => {
            (t._1, t._2.sum)
        })
        println(value1.collect().mkString(","))

        sc.stop()
    }
}
