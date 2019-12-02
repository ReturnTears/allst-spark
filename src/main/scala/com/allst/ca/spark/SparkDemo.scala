package com.allst.ca.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Spark WC
 *
  * @author YiYa
  * @since 2019-11-30 下午 12:51
  */
object SparkDemo {
    def main(args: Array[String]): Unit = {
        // 创建SparkConf对象,该对象包含应用信息
        val sparkConf = new SparkConf().setAppName("My Spark")
        // 创建SparkContext对象, 该对象可以访问Spark集群
        val sc = new SparkContext(sparkConf)

        val logFile = "E:\\TestData\\sparkJa.txt"

        val logData = sc.textFile(logFile, 2).cache()

        val numAs = logData.filter(line => line.contains("a")).count()

        val numBs = logData.filter(line => line.contains("b")).count()

        println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
    }
}
