package com.atguigu.scala.test05

import java.sql.{Connection, DriverManager}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkMysqlConnectTest {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("MysqlTest").setMaster("local[*]")
        val sc = new SparkContext(conf)

        //定义连接mysql的参数
        val driver = "com.mysql.jdbc.Driver"
        val url = "jdbc:mysql://127.0.0.1:3306/mydb0624"
        val userName = "root"
        val passWd = "root"
        val sql = "select * from users where id >=? and id <=?"

        val jdbcRDD = new JdbcRDD(
            sc,
            () => {
                Class.forName(driver)
                DriverManager.getConnection(url, userName, passWd)
            },
            sql,
            1,
            3,
            3,
            result=>(result.getInt(1),result.getString(2))
        )
        jdbcRDD.collect().foreach(println)

        sc.stop()
    }
}
