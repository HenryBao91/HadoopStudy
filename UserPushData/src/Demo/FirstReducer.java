package Demo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/** 第一个job，FirstReducer输出结果
 * TF统计为一个文件：
 *  关键字---id号	 出现次数
 *   九阳---001     2
 *   九阳---002     2
 *  N统计为另外一个文件： 
 * 	 count		1923
 * */
public class FirstReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {
		// process values
		
		int sum = 0 ;
		for (IntWritable i : values) {
			sum += i.get() ;
		}
		// 如果发现key值是count，说明要统计微博总条数
		if (key.equals(new Text("count"))) {
			// 打印微博总条数
			System.out.println(key.toString() +"__________"+ sum);
		}

	}

}
