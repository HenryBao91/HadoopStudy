package temper;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FirstPartition extends Partitioner<temperatureRank, Text>{
	// 按照年份进行分区
	public int getPartition(temperatureRank key, Text value, int num) {
		return (key.getYear()*127) % num;
	}

}
