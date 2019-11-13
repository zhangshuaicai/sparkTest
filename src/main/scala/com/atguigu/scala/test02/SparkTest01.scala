package com.atguigu.scala.test02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkTest01 {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
            conf.setAppName("Test01")
            conf.setMaster("local[*]")
        val sc = new SparkContext(conf)

        val fileRDD: RDD[String] = sc.textFile("input/agent.log")

        val arrRDD: RDD[Array[String]] = fileRDD.map(line => {
            line.split(" ")
        })

        val toupleRDD: RDD[(String, Array[String])] = arrRDD.map(arr => {
            (arr(1), arr)
        })
        val groupRDD: RDD[(String, Iterable[Array[String]])] = toupleRDD.groupByKey()
        val mapRDD: RDD[(String, Iterable[Array[String]], Int)] = groupRDD.map(t => {
            (t._1, t._2, t._2.size)
        })
        val sortRDD: RDD[(String, Iterable[Array[String]], Int)] = mapRDD.sortBy(x=>x._3,false)
        val tuples: Array[(String, Iterable[Array[String]], Int)] = sortRDD.take(3)
        val tuples1: Array[(String, Int)] = tuples.map(t => {
            (t._1, t._3)
        })
        println(tuples1.mkString(","))
        sc.stop()
    }
}
