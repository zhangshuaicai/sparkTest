package com.atguigu.scala.test02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 统计各个省份各个广告的点击数TOP3
 */
object SparkTest02 {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
            conf.setAppName("Test01")
            conf.setMaster("local[*]")
        val sc = new SparkContext(conf)

        val fileRDD: RDD[String] = sc.textFile("input/agent.log")
        //将数据转化成（省份-广告，数量）
        val arrRDD: RDD[(String, Int)] = fileRDD.map(line => {
            val datas: Array[String] = line.split(" ")
            (datas(1) + "-" + datas(4), 1)
        })
        //通过key将数据分组合并
        val groupByKeyRDD: RDD[(String, Iterable[Int])] = arrRDD.groupByKey()
        //将数据进行格式转换（省份-广告，数量）=>(省份，（广告，数量）)
        val mapRDD: RDD[(String, (String, Int))] = groupByKeyRDD.map {
            case (s: String, i: Iterable[Int]) => {
                val keys: Array[String] = s.split("-")
                (keys(0), (keys(1), i.sum))
            }
        }
        //对省份进行分组聚合
        val groupRDD: RDD[(String, Iterable[(String, Int)])] = mapRDD.groupByKey()
        //对每个组下的数据进行遍历，排序，选出TOP3
        val resultRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(datas => {
            datas.toList.sortWith((left, right) => {
                left._2 > right._2
            }).take(3)
        })
        resultRDD.collect().foreach(println)

        sc.stop()
    }
}
