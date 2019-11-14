package com.atguigu.scala.test03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkObjectTest01 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark join")
        val sc = new SparkContext(conf)
        val rdd: RDD[String] = sc.parallelize(Array("hello world", "hello atguigu", "atguigu", "hahah"), 2)
        val searcher = new Searcher("hello")
        val resultRDD: RDD[String] = searcher.getRDDContainQuery(rdd)
        resultRDD.foreach(println)
        sc.stop()
    }
}
//当Driver中实例化类时，调用类中的方法，会从executor中执行方法
//所以需要将driver中的对象传到executor中，需要实现Serializable
class Searcher(val query: String) extends Serializable {
    def getRDDContainQuery(rdd: RDD[String])={
        rdd.filter(_.contains(query))
    }
}
