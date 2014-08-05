package com.balab.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by balaji on 4/8/2014.
 */
public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final IntWritable one = new IntWritable(1);
    private Text word = new Text();

    protected void map(Object key, Text value, Context context) throws IOException,  InterruptedException {

        String[] csv = value.toString().split(",");

        for(String str:csv){
            word.set(str);
            context.write(word, one);
        }
    }
}
