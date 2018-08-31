package cz.hk.gmc.spark;

import java.io.IOException;
import java.net.URL;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Main {
    private static final String INPUT_FILE = "/tmp/national-parks.csv";
    //private static final String INPUT_FILE = "national-parks.csv";
    
    public static void main(final String[] arguments) {
        final Main demo = new Main();
        //demo.run();
        demo.runSpark();
    }
    
    private void runSpark() {
        final SparkConf sparkConf = new SparkConf()
                .setAppName("Example Spark App");
                //.setMaster("local[*]"); // remove this line for cluster run
        final JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //final URL url = Resources.getResource(INPUT_FILE);
        //final JavaRDD<String> stringJavaRDD = sparkContext.textFile(url.getPath());
        final JavaRDD<String> stringJavaRDD = sparkContext.textFile(INPUT_FILE);
        System.out.println("Number of lines in file = " + stringJavaRDD.count()); 
    }
    
    private void run() {
        final URL url = Resources.getResource(INPUT_FILE);
        try {
            final String content = Resources.toString(url, Charsets.UTF_8);
            System.err.println("MYTODO: " + content.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
