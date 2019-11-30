package com.allst.va.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * WordCount Java
 * @author June
 *              2018-11-11
 * @version 1.0
 */
public class JavaWc {
    /**
     * 正则表达式规则
     */
    private static final Pattern REGEX = Pattern.compile(" ");

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: JavaWordCount <file>");
            System.exit(1);
        }

        SparkConf conf = new SparkConf().setAppName("JavaWc").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        // 需要指定参数 : 文件目录和分区数
        JavaRDD<String> lines = sc.textFile(args[0], Integer.parseInt(args[1]));

        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(REGEX.split(s)).iterator();
            }
        });

        JavaPairRDD<String, Integer> pairs = words.mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = pairs.reduceByKey((Function2<Integer, Integer, Integer>) (i1, i2) -> i1 + i2);

        List<Tuple2<String, Integer>> output = counts.collect();

        for (Tuple2<?, ?> tuple : output) {
            System.out.println(tuple._1 + ":" + tuple._2);
        }
        sc.stop();
    }
}
