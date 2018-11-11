package com.allst.spark.demo;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * Spark2.0以上 SparkSession替代了SparkContext
 * @author June
 * 2018-08-14
 * @version 1.0
 */
public class SimpleDemo {
    public static void main(String[] args) {
        // hdfs上的文件，默认目录是hdfs://shizhan:9000/user/root
        String logFile = "hdfs://shizhan:9000/user/root/log.txt";
        // file:\\从本地文件夹获取文件
        // String localFile = "E:\\TestData\\sparkJa.txt";
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("t")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("w")).count();

        System.out.println("Lines with a :" + numAs + ", lines with b :" + numBs);
        spark.stop();
    }
}
