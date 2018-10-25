package temper;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FirstPartition extends Partitioner<keyPair, Text>{
	
	// 重新自定义分区的方法
	public int getPartition(keyPair key, Text value, int num) {
		// 年份在key里面
		return (key.getYear()*127) % num;
	}

}
