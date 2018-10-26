package Demo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.HashPartitioner;

/**
 * ���keyΪcount�������ֳ�һ��������3 ��
 * ������key��ƽ��������������
 * */

public class FirstPartition extends HashPartitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		// TODO Auto-generated method stub
		if (key.equals(new Text("count"))) {
			return 3 ; 
		}
		return 
			// ��key���ȷֲ���numReduceTasks-1 �� 
			super.getPartition(key, value, numReduceTasks-1);
	}
	
	
}
