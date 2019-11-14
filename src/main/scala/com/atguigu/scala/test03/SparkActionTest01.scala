package com.atguigu.scala.test03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkActionTest01 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark join")
        val sc = new SparkContext(conf)

        val rdd1 = sc.parallelize(1 to 100)
        //聚合函数，先聚合分区内的数据，在聚合分区间的数据
        val i: Int = rdd1.reduce(_+_)
        val l: Long = rdd1.count()
//        println(i)
//        println(l)

        val ints = List(1,5,2,3,7,4,6)
        val listRDD: RDD[Int] = sc.parallelize(ints)
        //takeOrdered 先对RDD中的数据进行排序后，再取出相应的数据
        val takes: Array[Int] = listRDD.takeOrdered(3)
//        takes.foreach(println)

        //aggregate 和 aggregateByKey 唯一区别是参数类型
        val i1: Int = listRDD.aggregate(0)(_+_,_+_)
//        println(i1)
        //fold （折叠函数） 是 aggregate 是简化版，当 分区内操作 和 分区间操作 相同时可以使用fold函数
        val i2: Int = listRDD.fold(0)(_+_)
//        println(i1)

        //将数据集的元素以textfile的形式保存到HDFS文件系统或者其他支持的文件系统，
        // 对于每个元素，Spark 将会调用toString方法，将它装换为文件中的文本
        //listRDD.saveAsTextFile("")
        //用于将 RDD 中的元素序列化成对象，存储到文件中
        //listRDD.saveAsObjectFile("")
        //将数据集中的元素以 Hadoop sequencefile 的格式保存到指定的目录下，可以使 HDFS 或者其他 Hadoop 支持的文件系统
        //listRDD.map((_,1)).saveAsSequenceFile("")

        val rdd2 = sc.parallelize(Array(("a", 10), ("a", 20), ("b", 100), ("c", 200)))
        //可以用来查看数据是否倾斜
        val countMap: collection.Map[String, Long] = rdd2.countByKey()
        println(countMap)

        //针对 RDD 中的每个元素都执行一次func,每个函数是在 Executor 上执行的, 不是在 driver 端执行的
        //因为是executor上分布式执行，所以数据是乱序执行的
        listRDD.foreach(println)
        sc.stop()
    }
}
