package com.allst.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;
import java.util.List;

/**
 * 算子介绍 - Map
 * @author June
 *              2018-11-11
 * @version 1.0
 */
public class MapOptr {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("MapOptr").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> list = Arrays.asList("hello,world","hello,yangY");
        JavaRDD<String> linesRDD = sc.parallelize(list);
        
        JavaRDD<Object> mapRDD = linesRDD.map((Function<String, Object>) s -> s.split(","));
        
        JavaRDD<String> flatMapRDD = linesRDD.flatMap((FlatMapFunction<String, String>) o -> Arrays.asList(o.split(",")).iterator());

        List<Object> collect = mapRDD.collect();
        // 打印的是对象的句柄,因为对Array.map后生成的Array(Array(hello, world), Array(hello, yangY))
        System.out.println(collect);
        // 打印hello  world hello yangY
        List<String> collec = flatMapRDD.collect();
        for (String str : collec) {
            System.out.println(str);
        }
    }
}
