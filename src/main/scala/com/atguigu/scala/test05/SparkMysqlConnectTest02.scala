package com.atguigu.scala.test05

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.{SparkConf, SparkContext}

object SparkMysqlConnectTest02 {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("MysqlTest").setMaster("local[*]")
        val sc = new SparkContext(conf)

        //定义连接mysql的参数
        val driver = "com.mysql.jdbc.Driver"
        val url = "jdbc:mysql://127.0.0.1:3306/mydb0624"
        val userName = "root"
        val passWd = "root"
        val sql = "insert into users values(?,?,?,?)"

        val users = Array((4,"刘超",33,"g@G.com"),(5,"李健",22,"f@f.com"))

        val paraRDD: RDD[(Int, String, Int, String)] = sc.parallelize(users)

        //对每个分区执行
        paraRDD.foreachPartition(it=>{
            Class.forName(driver)
            val conn: Connection = DriverManager.getConnection(url,userName,passWd)
            it.foreach(x=>{
                val preparedStatement: PreparedStatement = conn.prepareStatement(sql)
                preparedStatement.setInt(1,x._1)
                preparedStatement.setString(2,x._2)
                preparedStatement.setInt(3,x._3)
                preparedStatement.setString(4,x._4)
                preparedStatement.executeUpdate()
                preparedStatement.close()
            })
            conn.close()
        })

        sc.stop()
    }
}
