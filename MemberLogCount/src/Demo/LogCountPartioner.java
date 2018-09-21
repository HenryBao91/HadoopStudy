package Demo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 1、自定义Partioner实现将社交网站用户每天登录次数的统计结果根据不同的月份
 * 分发到不同的输出文件里。在getPartioner方法里，分别使用0、1与numPartioners
 * 相除求余。
 * 2、因为本程序只需要1月和2月的数据，所以只需要把数据根据月份分到两个不同的输出中即可，
 * 即numPartioners的值是2，这样0%1、1%2正好是两个不同的值。
 * 3、使用Partioner还需要在驱动类里面设置Partioner类及Reducer个数。 
 *  
 */
public class LogCountPartioner extends Partitioner<MemberLogTime,IntWritable>{

	@Override
	public int getPartition(MemberLogTime key, IntWritable value, int numPartioners) {
		// TODO Auto-generated method stub
		String date = key.getLogTime() ;
		if (date.contains("2016-01") ) {
			return 0 % numPartioners ; 
		}
		return 1 % numPartioners ; 
	}

}
