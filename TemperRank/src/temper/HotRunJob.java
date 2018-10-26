package temper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class HotRunJob {

	public static void main(String[] args) throws Exception {
	
		Configuration conf = new Configuration();
		conf.set("mapreduce.job.jar", JarUtil.jar(HotRunJob.class));
		Job job = Job.getInstance(conf, "Temper Sort Rank");
		
		job.setJarByClass(temper.HotRunJob.class);
		job.setMapperClass(temper.HotMapper.class);	
		job.setReducerClass(temper.HotReducer.class);
		
		// Map 输出格式设置
	    job.setMapOutputKeyClass(keyPair.class);
	    job.setMapOutputValueClass(Text.class);
		
		// TODO: specify output types
	    job.setNumReduceTasks(6);   // 设置reduce数量，要和年份个数保持一致
	    job.setPartitionerClass(temper.FirstPartition.class);
	    job.setSortComparatorClass(temper.SortHot.class);   
	    job.setGroupingComparatorClass(temper.GroupHot.class);      
	    
		// TODO: specify input and output DIRECTORIES (not files)
	    FileInputFormat.addInputPath(job, new Path("hdfs://server01:9000/input/hot"));   
	    FileOutputFormat.setOutputPath(job, new Path("hdfs://server01:9000/output/hot"));

		if (!job.waitForCompletion(true))
			return;
	}

}
