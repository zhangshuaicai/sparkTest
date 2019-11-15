package com.atguigu.scala.test05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkJsonTest {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("JSON Test")
        val sc = new SparkContext(conf)

        val path = "input/emp.json"

        val jsonFileRDD: RDD[String] = sc.textFile(path)
        import scala.util.parsing.json.JSON
        val mapJsonRDD: RDD[Option[Any]] = jsonFileRDD.map(JSON.parseFull)
        mapJsonRDD.collect().foreach(println)
        sc.stop()

    }
}
