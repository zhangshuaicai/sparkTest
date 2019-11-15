package com.atguigu.scala.test04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount02 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(conf)

        val path = "D:\\input\\num.txt"

        val lines = sc.textFile(path)

        val words = lines.flatMap(line => line.split("\t"))

        val word = words.map(word => {
            new Tuple2(word, 1)
        })
        //方式二：先分组->再对数量进行合并->算数量的合
        val wordmap = word.groupBy(k=>k._1)

        val wordmaplist = wordmap.map(word => {
            val count = word._2.map(k=>(k._2))
            (word._1, count)
        })
        val value = wordmaplist.map(word => {
            (word._1, word._2.sum)
        })
        value.foreach(println)

        sc.stop()
    }
}
