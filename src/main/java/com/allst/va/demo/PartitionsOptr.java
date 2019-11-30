package com.allst.va.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author June
 *              2018-11-11
 * @version 1.0
 */
public class PartitionsOptr {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("FlatMapOptr").setMaster("local[1]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        List<String> list = Arrays.asList("aa1", "bb1", "bb1", "aa2", "bb2", "bb2", "aa3", "bb3", "bb3");
        partitionOptr(sc, list);

    }

    private static  void partitionOptr(JavaSparkContext sc, List<String> list) {
        JavaRDD<String> rdd = sc.parallelize(list, 3);
        JavaRDD<String> mapParatitions = rdd.mapPartitions(new FlatMapFunction<Iterator<String>, String>() {
            int count = 0;
            @Override
            public Iterator<String> call(Iterator<String> stringIterator) throws Exception {
                List<String> lst = new ArrayList<>();
                while (stringIterator.hasNext()) {
                    lst.add("count:" + count++ + "\t" + stringIterator.next());
                }
                return lst.iterator();
            }
        });

        List<String> result = mapParatitions.collect();
        for (String s : result) {
            System.out.println(s);
        }

        sc.stop();
    }
}
