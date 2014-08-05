package com.balab.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by balaji on 4/8/2014.
 */
public class WordCountReducer extends Reducer{

    protected void reduce(Text text, Iterable<IntWritable> values,   Context context) throws IOException, InterruptedException{
        int sum = 0;

        for( IntWritable value :values){
            sum += value.get();
        }

        context.write(text, sum);
    }
}
