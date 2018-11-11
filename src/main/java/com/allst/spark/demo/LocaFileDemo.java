package com.allst.spark.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * 从本地文件夹获取文件
 * @author June
 *          2018-11-11
 * @version 1.0
 */
public class LocaFileDemo {
    public static void main(String[] args) {
        // local[t] : local模式, t表示线程数量
        SparkConf conf = new SparkConf().setAppName("LocalFile").setMaster("local[2]");
//        SparkContext sc = new SparkContext(conf);
        JavaSparkContext sc = new JavaSparkContext(conf);
        testSpark04(sc);
        sc.stop();
    }

    /**
     * 模板方法
     * @param sc
     */
    private static void testSpark00(JavaSparkContext sc) {

    }

    /**
     * // 生成RDD方法之一 : Parallelized Collections
     * @param sc
     */
    private static void testSpark01(JavaSparkContext sc) {
        List<Integer> data = Arrays.asList(1,2,3,4,5,6);
        JavaRDD<Integer> distData = sc.parallelize(data);
        System.out.println(distData.count());
    }

    /**
     * // 生成RDD方法之二: External Datasets
     * @param sc
     */
    private static void testSpark02(JavaSparkContext sc) {
        JavaRDD<String> distFile = sc.textFile("E:\\TestData\\sparkJa.txt");
        JavaRDD<Integer> lines =  distFile.map(s -> s.length());
        lines.persist(StorageLevel.MEMORY_AND_DISK());
        int totals = lines.reduce((a, b) -> a + b);
        System.out.println(totals);
    }

    /**
     * 上述testSpark02()方法中的也可以使用函数重写的方式
     * @param sc
     */
    private static void testSpark03(JavaSparkContext sc) {
        JavaRDD<String> dataRdd = sc.textFile("E:\\TestData\\sparkJa.txt");
        JavaRDD<Integer> linesLen = dataRdd.map(new GetLength());
        int strLen = linesLen.reduce(new Sum());
        System.out.println(strLen);
    }

    private static void testSpark04(JavaSparkContext sc) {
        JavaRDD<String> lines = sc.textFile("E:\\TestData\\sparkJa04.txt");
        JavaPairRDD<String, Integer> pairRDD = lines.mapToPair(s -> new Tuple2(s, 1));
        JavaPairRDD<String, Integer> counts = pairRDD.reduceByKey((a, b) -> a + b);
        System.out.println(counts.collect());
    }
}
/**
 * 重写map()中的函数
  */
class GetLength implements Function<String, Integer> {
    @Override
    public Integer call(String s) throws Exception {
        return s.length();
    }
}

/**
 * 重写reduce()中的函数
 */
class Sum implements Function2<Integer, Integer, Integer> {
    @Override
    public Integer call(Integer a, Integer b) throws Exception {
        return a + b;
    }
}