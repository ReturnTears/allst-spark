package com.allst.ca.spark

import org.apache.spark.SparkContext

/**
  * @author YiYa
  * @since 2019-12-02 下午 11:34
  */
object SparkDemo2 {
    def main(args: Array[String]): Unit = {
        System.setProperty("hadoop.home.dir", "E:\\appPackage\\hadoop\\hadoop-2.8.1");
        val sc = new SparkContext("local", "My App")
        val line = sc.textFile("E:\\TestData\\test\\log.txt")

        line.map((_, 1)).reduceByKey(_+_).collect().foreach(println)

        sc.stop()

        println(111111)
    }
}
