package ToolRunnerDemo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogCountReducer extends 
	Reducer<MemberLogTime, IntWritable, MemberLogTime, IntWritable> {

	public void reduce(MemberLogTime key, Iterable<IntWritable> values, 
			Reducer<MemberLogTime, IntWritable, MemberLogTime, IntWritable>.Context context) 
			throws IOException, InterruptedException {
		
		// 自定义动态计数器对1月和2月用户每天登录次数的输出结果进行计数
		if (key.getLogTime().contains("2016-01")) {
			context.getCounter("OutputCounter","JanuaryResult").increment(1);
		} else if (key.getLogTime().contains("2016-02")) {
			context.getCounter("OutputCounter","FebruaryResult").increment(1);
		}
		
		// process values
		int sum = 0 ;
		for (IntWritable val : values) {
			sum += val.get();
		}
		context.write(key, new IntWritable(sum));
	}

}
