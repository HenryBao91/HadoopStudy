package temper;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RunJob {
	
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static class HotMapper extends Mapper<LongWritable, Text, temperatureRank, Text>{

		@Override
		protected void map(LongWritable key, Text value,
				Context context)
				throws IOException, InterruptedException {
			String line = value.toString() ;
			String[] ss = line.split("\t") ;
			if (ss.length == 2) {
				try {
					Date date = SDF.parse(ss[0]) ;
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					int year = c.get(1) ;  //年份
					String hot = ss[1].substring(0, ss[1].indexOf("°C")) ;
					temperatureRank kp = new temperatureRank() ;
					kp.setYear(year);
					kp.setHot(Integer.parseInt(hot));
					context.write(kp, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	static class HotReduce extends Reducer<temperatureRank, Text, temperatureRank, Text>{

		protected void reduce(temperatureRank kp, Iterable<Text> value,
				Context context)
				throws IOException, InterruptedException {
			for(Text v : value)
				context.write(kp, v);
		}
		
	}	
	
	public static void main(String[] args) {
	    Configuration conf = new Configuration();
	    try {
		    Job job = new Job(conf, "Hot Sort");
		    job.setJarByClass(RunJob.class);
		    job.setMapperClass(HotMapper.class);
		    job.setReducerClass(HotReduce.class);
		    job.setMapOutputKeyClass(temperatureRank.class);
		    job.setMapOutputValueClass(Text.class);
		    
		    job.setNumReduceTasks(5);   // 每个年份对应一个reduce
		    job.setPartitionerClass(FirstPartition.class);
		    job.setSortComparatorClass(SortHot.class);
		    job.setGroupingComparatorClass(GroupHot.class);
		    FileInputFormat.addInputPath(job, new Path("/input/hot"));   
		    FileOutputFormat.setOutputPath(job, new Path("/output/hot"));
		    System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();    
		}

	}
}







	
	
	
	
	
	
	
	
	
	
	
	