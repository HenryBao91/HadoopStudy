package Demo;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class MyMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable ikey, Text ivalue, Context context) 
			throws IOException, InterruptedException {
		// 每行数据是一组好友关系
		String line = ivalue.toString();
		// 通过间隔符截取,不使用StringTokenizer,因为主/从，即key/value同时都要
		String[] str = line.split("\t");
		// 每组好友关系输出两次，即hello you ，you hello
		context.write(new Text(str[0]), new Text(str[1]));
		context.write(new Text(str[1]), new Text(str[0]));		
	}

}
