package com.atguigu.scala.test05

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object MyPartitionerDemo {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("Practice").setMaster("local[*]")

        val sc = new SparkContext(conf)

        val rdd1 = sc.parallelize(Array((10, "a"), (20, "b"), (30, "c"), (40, "d"), (50, "e"), (60, "f")),3)

        val par1: RDD[(Int, String)] = rdd1.partitionBy(new MyPartitioner(4))

        val rdd3: RDD[(Int, String)] = par1.mapPartitionsWithIndex((index, items) => items.map(x => (index, x._1 + " : " + x._2)))

        println(rdd3.collect.mkString(" "))
    }
}
class MyPartitioner(numPartition : Int) extends Partitioner{
    override def numPartitions: Int = numPartition

    override def getPartition(key: Any): Int = 1
}
