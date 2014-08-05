package com.balab.hadoop;

import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException,
                                                    InterruptedException, ClassNotFoundException{

        System.out.println( " Starting Hadoop" );

        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);

        WordCount  wordCount = new WordCount(inputPath, outputPath);


    }
}
