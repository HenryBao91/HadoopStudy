package SubmitDemo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 	声明Combiner类外，还需要在驱动类里面配置Combiner类。
 * 	有时候不必特意声明一个Combiner类。当Combiner和Reducer的实现逻辑相同的
 * 	时候，可以不用声明Combiner类，而在驱动类里面添加代码即可。
 * */
public class LogCountCombiner extends Reducer<MemberLogTime, IntWritable, 
			MemberLogTime, IntWritable> {

	
	protected void reduce(MemberLogTime key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {
		// process values
		int sum = 0 ;
		for (IntWritable val : values) {
			sum += val.get() ;
		}
		context.write(key, new IntWritable(sum) );
	}

}
