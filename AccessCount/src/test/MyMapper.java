/**
 * Mapper处理逻辑伪代码：
 * Begin
 * 	自定义MyMapper类，继承Mapper;
 * 		定义初始次数1 ;
 * 		读取用户访问日志文件	;
 * 		以每一行为单位，以逗号为分隔符进行分析 ;
 * 		将结果存入Array数组	;
 * 		将数组中的第二个元素与初始次数组合后输出，格式为<访问日期,1> ;
 * 	End
 */
package test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.mockito.internal.stubbing.answers.ThrowsException;

//  Mapper 模块
public class MyMapper extends Mapper<Object, Text, Text, IntWritable>{
	
	// 定义初始次数1
	private final static IntWritable one = new IntWritable(1) ; 
	
	// map 函数处理逻辑
	public void map(Object key , Text value , Context context) 
			throws IOException, InterruptedException{	
		// 以每一行为单位，进行字符串转换
		String line = value.toString() ;
		// 以逗号为分隔符，组成数组
		String array[] = line.split(",") ;
		// 提取数组中访问日期作为Key
		String keyOutput = array[1] ;
		// 将访问日期与初始次数1，组成键值对，context:<Text , IntWritable>
		context.write(new Text(keyOutput), one);
	} 
}
