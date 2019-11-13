import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.Tuple2;
import scala.collection.TraversableOnce;

import java.util.Arrays;
import java.util.Iterator;

public class JavaWordCount {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
            conf.setAppName("JAVA WORD COUNT");
            conf.setMaster("local");
        JavaSparkContext context = new JavaSparkContext(conf);

        String path = "D:\\input\\num.txt";

        JavaRDD<String> lines = context.textFile(path,1);

        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String line) throws Exception {
                return Arrays.asList(line.split("\t")).iterator();
            }
        });
//        JavaRDD<String> words1 = lines.flatMap((FlatMapFunction<String, String>) line ->Arrays.asList(line.split("\t")).iterator());

        JavaPairRDD<String, Integer> pairRDD = words.mapToPair(new PairFunction<String, String, Integer>() {

            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });

        JavaPairRDD<String, Integer> result = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        result.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                System.out.println(stringIntegerTuple2);
            }
        });


        context.stop();

    }
}
