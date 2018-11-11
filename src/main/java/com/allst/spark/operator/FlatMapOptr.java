package com.allst.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

/**
 * 算子介绍 - flatMap
 * @author June
 *              2018-11-11
 * @version 1.0
 */
public class FlatMapOptr {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("FlatMapOptr").setMaster("local[1]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<String> list = Arrays.asList("aa,bb,cc", "cxf,spring,struts2", "java,C++,javaScript");
        flatMapOptr(sc, list);
    }


    private static void flatMapOptr(JavaSparkContext sc, List<String> list) {
        JavaRDD<String> rddData = sc.parallelize(list);
        JavaRDD<String> flatMapData = rddData.flatMap((FlatMapFunction<String, String>) s -> Arrays.asList(s.split(",")).iterator());
        flatMapData.foreach((VoidFunction<String>) s -> System.out.println(s));
        sc.stop();
    }
}
