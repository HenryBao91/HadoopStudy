/**
 * Reducer处理逻辑伪代码
 * Begin
 * 	 自定义MyReducer类，继承Reducer ;
 * 	      覆写reducer函数 ;
 * 		读取Mapper输出键值对 ;
 * 		把相同建的值进行累加 	;
 * 		输出<访问日期，总访问次数> ;
 * End
 */
package test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// Reducer模块
public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	// 创建 IntWritable
	private IntWritable result = new IntWritable()  ;
	
	public void reduce(Text key , Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {
		// 定义累加器，初始值为0
		int sum = 0 ;
		for (IntWritable val : values) {
			// 将相同键的所有值进行累加
			sum += val.get() ;
		}
		result.set(sum);
		context.write(key, result);
	}
}
