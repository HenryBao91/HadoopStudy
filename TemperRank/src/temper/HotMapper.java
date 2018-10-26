package temper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class HotMapper extends Mapper<LongWritable, Text, keyPair, Text> {

	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {
			
		String line = value.toString() ;
		String[] ss = line.split("\t") ;
		if (ss.length == 2) {
			try {
				// 字符串转日期，得到日期
				Date date = SDF.parse(ss[0]) ;
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				int year = c.get(1) ;  //得到年份
				String hot = ss[1].substring(0, ss[1].indexOf("°C")) ;  // 得到温度值

				// 输出key值
				keyPair kp = new keyPair() ;
				kp.setYear(year);
				kp.setHot(Integer.parseInt(hot));
				
				context.write( kp, new Text(value) );
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
