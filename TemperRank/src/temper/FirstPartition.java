package temper;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FirstPartition extends Partitioner<temperatureRank, Text>{
	// ������ݽ��з���
	public int getPartition(temperatureRank key, Text value, int num) {
		return (key.getYear()*127) % num;
	}

}
