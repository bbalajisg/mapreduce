package com.balab.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by balaji on 4/8/2014.
 */
public class WordCount {

    public WordCount( Path inputPath, Path outputPath) throws IOException, ClassNotFoundException, InterruptedException {

        // Create configuration
        Configuration conf = new Configuration(true);

        // Create job
        Job job = new Job(conf, "WordCount");
        job.setJarByClass(WordCountMapper.class);

        // Setup MapReduce
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setNumReduceTasks(1);

        // Specify key / value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //Input
        FileInputFormat.addInputPath(job, inputPath);
        job.setInputFormatClass(TextInputFormat.class);

        //Output
        FileOutputFormat.setOutputPath(job, outputPath);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileSystem hdfs = FileSystem.get(conf);
        if(hdfs.exists(outputPath)){
            hdfs.delete(outputPath, true);
        }

        int code = job.waitForCompletion(true) ? 0 : 1;
        System.exit(code);
    }
}
