package com.atguigu.scala.test02

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object SparkOperator03 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf()
        conf.setMaster("local[*]")
        conf.setAppName("Spark Creation")
        val sc: SparkContext = new SparkContext(conf)

        val arrRDD: RDD[Int] = sc.parallelize(1 to 10, 4)

        //每次处理一条数据
        //val mapRDD: RDD[Int] = arrRDD.map(_*2)

        //mapPartitions实在独立分区上做操作，
        //当处理完这个分区，原分区才会被释放掉，数据量过大的时候会出现OOM
        //val mapRDD: RDD[Int] = arrRDD.mapPartitions(datas => datas.map(_*2))

        //val mapRDD: RDD[(Int, Int)] = arrRDD.mapPartitionsWithIndex((index, datas) => {
        //    datas.map((index, _))
        //})

        //先进行map操作，再进行扁平化操作
        //val mapRDD: RDD[Int] = arrRDD.flatMap(x => Array(x*x,x*x*x))

        //将每个分区中的数据合并成一个数组，当数据过大的时候，容易OOM
        //val mapRDD: RDD[Array[Int]] = arrRDD.glom()

        //
        //val mapRDD: RDD[(String, Iterable[Int])] = arrRDD.groupBy(x=>if(x%2!=0)"even" else "odd")

        //val arrs: Array[String] = Array("ab","ab","ab","bc","bc")

        //val mapRDD: Array[String] = arrs.filter(_.contains("a"))

        //抽样算子：withReplacement: Boolean,是否放回  fraction: Double,概率 seed:种子
        //val mapRDD: RDD[Int] = arrRDD.sample(false,0.5)

        //去重算子：参数：任务的数量，默认值和分区数保持一致
        //val arrsRDD: RDD[Int] = sc.parallelize(Array(1,2,1,2,3,4,5))
        //val mapRDD: RDD[Int] = arrsRDD.distinct()

        //减小分区到指定数量,用于大数据集过滤后，提高小数据集的执行效率
        //println(arrRDD.partitions.length)
        //val rdd: RDD[Int] = arrRDD.coalesce(2)
        //println(rdd.partitions.length)

        //根据新的分区数，重新shuffle所有的数据，分区数大小不限
        //coalesce 可以选择进行shuffle，而repartition是调用coalesce，进行shuffle的
        //val rdd1: RDD[Int] = arrRDD.repartition(3)
        //println(rdd1.partitions.length)
        //val rdd2: RDD[Int] = arrRDD.repartition(6)
        //println(rdd2.partitions.length)

        //排序，默认true升序
        //val arr1: RDD[Int] = arrRDD.sortBy(x=>x)
        //val arr2: RDD[Int] = arrRDD.sortBy(x=>x,false)
        //println(arr1.collect().mkString(","))
        //println(arr2.collect().mkString(","))

        //交集，并集，差集，笛卡尔积，拉链
        /*val rdd1: RDD[Int] = sc.parallelize(1 to 6,2)
        val rdd2: RDD[Int] = sc.parallelize(4 to 9,2)
        val rdd3: RDD[Int] = rdd1.union(rdd2) //并集
        val rdd4: RDD[Int] = rdd1.subtract(rdd2) //差集
        val rdd5: RDD[Int] = rdd1.intersection(rdd2) //交集
        val rdd6: RDD[(Int, Int)] = rdd1.cartesian(rdd2) //笛卡尔积
        //Can only zip RDDs with same number of elements in each partition
        //Can't zip RDDs with unequal numbers of partitions: List(2, 4)
        val rdd7: RDD[(Int, Int)] = rdd1.zip(rdd2)
        println(rdd3.collect().mkString(","))
        println(rdd4.collect().mkString(","))
        println(rdd5.collect().mkString(","))
        println(rdd6.collect().mkString(","))
        println(rdd7.collect().mkString(","))*/

        //        println(mapRDD.collect().mkString(","))

        //val rdd1: RDD[(Int, String)] = sc.parallelize(Array((1, "a"), (2, "b"), (3, "c"), (4, "d")))
        //val value: RDD[(Int, String)] = rdd1.partitionBy(new HashPartitioner(3))
        //println(value.partitions.length)


        //1.	reduceByKey：按照key进行聚合，在shuffle之前有combine（预聚合）操作，返回结果是RDD[k,v]。
        //2.	groupByKey：按照key进行分组，直接进行shuffle。
        //3.	开发指导：reduceByKey比groupByKey性能更好，建议使用。但是需要注意是否会影响业务逻辑。
        /*val listRDD: RDD[(String, Int)] = sc.parallelize(List(("female",1),("male",5),("female",5),("male",2)))

        val value: RDD[(String, Int)] = listRDD.reduceByKey(_+_)
        println(value.collect().mkString(","))

        val groupRDD: RDD[(String, Iterable[Int])] = listRDD.groupByKey()
        val value1: RDD[Any] = groupRDD.map(t => {
            (t._1, t._2.sum)
        })
        println(value1.collect().mkString(","))*/

        val rdd = sc.parallelize(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
        // aggregateByKey 先对分区内进行操作，之后再对区分间进行操作
        // 第一个参数 zerovalue初始值，当分区内只有一个数据时，会和初始值进行比较
        // 第二个参数 第一个放分区内的操作
        // 第二个参数 第二个放分区间的操作
        val value: RDD[(String, Int)] = rdd.aggregateByKey(Int.MinValue)(math.max(_, _), _ + _)

        //第一个参数做类型的转换
        //第二个参数放分区内的操作
        //第三个参数放分区间的操作
        val value1: RDD[(String, (Int, Int))] = rdd.combineByKey(
            (x: Int) => (x, 1),//createCombiner 是将分区中的每种key调用一次，变成其他数据格式，其他数据不做改变
            (x: (Int, Int), y: Int) => (x._1 + y, x._2 + 1),//将数据相加，计数+1
            (x: (Int, Int), y: (Int, Int)) => (x._1 + y._1, x._2 + y._2)//将各个分区中的数据进行合并
        )
        val value2: RDD[(String, Int)] = value1.map {
            case ((s: String, (x: Int, y: Int))) => {
                (s, x / y)
            }
        }
        println(value2.collect().mkString(","))
        sc.stop()
    }
}
