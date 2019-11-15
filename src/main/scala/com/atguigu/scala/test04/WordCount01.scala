package com.atguigu.scala.test04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount01 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(conf)

        val path = "D:\\input\\num.txt"

        val lines = sc.textFile(path)

        val words = lines.flatMap(line => line.split("\t"))

        val word = words.map(word => {
            new Tuple2(word, 1)
        })
        //方式一：reduceByKey
        val result: RDD[(String, Int)] = word.reduceByKey(_+_)

        println(result.collect().mkString(","))

        sc.stop()
    }
}
