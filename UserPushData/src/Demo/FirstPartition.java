package Demo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.HashPartitioner;

/**
 * 如果key为count，单独分成一个分区：3 ；
 * 其他的key：平均分配三个分区
 * */

public class FirstPartition extends HashPartitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		// TODO Auto-generated method stub
		if (key.equals(new Text("count"))) {
			return 3 ; 
		}
		return 
			// 将key均匀分布在numReduceTasks-1 上 
			super.getPartition(key, value, numReduceTasks-1);
	}
	
	
}
