package com.atguigu.scala.test02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkOperator04 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark join")
        val sc = new SparkContext(conf)

        var rdd1 = sc.parallelize(Array((1, "a"), (1, "b"), (2, "a")))
        var rdd2 = sc.parallelize(Array((1, "d"), (1, "e"), (2, "b")))
        var rdd3 = sc.parallelize(Array((1, "f"), (1, "gb"), (2, "c")))
        //两个rdd中，key和key相同时，把value合并到一起，依次合并，组成（key，value）格式
        //也支持外连接: leftOuterJoin, rightOuterJoin, and fullOuterJoin
        //数据大时，不建议使用
        val value: RDD[(Int, (String, String))] = rdd1.join(rdd2)
        value.collect().foreach(println)
        //cogroup 是将相同key在不同的RDD中的value汇总成多个tuple放到一个CompactBuffer中
        val value1: RDD[(Int, (Iterable[String], Iterable[String], Iterable[String]))] = rdd1.cogroup(rdd2,rdd3)
        value1.collect().foreach(println)
        sc.stop()
    }
}
