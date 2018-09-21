package test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// Mapper 模块
public class MyMapper extends Mapper<Object, Text, IntWritable, Text> {

	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		// 按行字符串处理
		String line = value.toString() ;
		// 指定Tab为分隔符，组成数组
		String array[] = line.split("\t") ;
		
		// 提取访问次数作为Key, Integer.parseInt()方法，将字符串转换成整数
		int keyoutput = Integer.parseInt(array[1]) ;
		// 提取日期作为value值
		String valueoutput = array[0] ;
		// 组成键值对,输出格式（IntWritable，valueoutput）
		context.write(new IntWritable(keyoutput), new Text(valueoutput) );
	}

}
