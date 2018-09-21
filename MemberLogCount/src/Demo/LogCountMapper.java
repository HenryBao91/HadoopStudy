package Demo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogCountMapper extends Mapper<Text, Text, MemberLogTime, IntWritable> {

	// 定义登录日期和变量one
	private MemberLogTime mTime = new MemberLogTime();
	private IntWritable one = new IntWritable(1) ;
	
	// 定义枚举类型计数器
	enum LogCounter{
		January ,
		February 
	}
	
	public void map(Text key, Text value, 
			Mapper<Text, Text, MemberLogTime,IntWritable>.Context context) 
			throws IOException, InterruptedException {
		// 读取用户名	
		String member_name = key.toString() ;
		String logTime = value.toString() ;

		// 使用计数器
		if (logTime.contains("2016-01")) {
			context.getCounter(LogCounter.January).increment(1);
		} else if (logTime.contains("2016-02")) {
			context.getCounter(LogCounter.February).increment(1);
		} 
		// 设置用户名和登录时间
		mTime.setMember_name(member_name);
		mTime.setgetLogTime(logTime);
		
		context.write(mTime, one);
	}



}
