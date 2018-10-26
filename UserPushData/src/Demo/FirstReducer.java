package Demo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/** ��һ��job��FirstReducer������
 * TFͳ��Ϊһ���ļ���
 *  �ؼ���---id��	 ���ִ���
 *   ����---001     2
 *   ����---002     2
 *  Nͳ��Ϊ����һ���ļ��� 
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
		// �������keyֵ��count��˵��Ҫͳ��΢��������
		if (key.equals(new Text("count"))) {
			// ��ӡ΢��������
			System.out.println(key.toString() +"__________"+ sum);
		}

	}

}
