import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FindMaxTemp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length !=2){
            System.err.println("Input: FindMatTemp <input path> <output path>");
            System.exit(-1);
        }
        Job job = new Job();
        job.setJarByClass(FindMaxTemp.class);
        job.setJobName("Find Max temp");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(FindMaxTempMapper.class);
        job.setReducerClass(FindMaxTempReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }    
}