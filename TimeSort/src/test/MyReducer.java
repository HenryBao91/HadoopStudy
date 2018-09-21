package test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

// Reduce模块
// 在Map传至Reduce
public class MyReducer extends Reducer<IntWritable, Text, Text, IntWritable> {

	public void reduce(IntWritable key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
		// process values
		for (Text val : values) {
			context.write(val, key);
		}
	}

}
