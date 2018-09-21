package test;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MySelectMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable ikey, Text ivalue, 
			Mapper<LongWritable, Text, Text, Text>.Context context) 
			throws IOException, InterruptedException {
		String val[] = ivalue.toString().split(",") ;
		// 过滤选取1月和2月的数据
		if(val[1].contains("2016-01") || val[1].contains("2016-02") ) {
			context.write(new Text(val[0]), new Text(val[1]) );
		}
	}

}
