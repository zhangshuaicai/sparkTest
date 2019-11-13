package com.atguigu.scala.test01

import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCount {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
            conf.setAppName("Word Count")
            conf.setMaster("local")
        val context = new SparkContext(conf)

        val path = "D:\\input\\num.txt"

        val lines = context.textFile(path)

        val words = lines.flatMap(line => line.split("\t"))

        val word = words.map(word => {
            new Tuple2(word, 1)
        })
        //方式一：reduceByKey
        val result = word.reduceByKey(_+_)
        result.foreach(println)

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
        context.stop()
    }
}
