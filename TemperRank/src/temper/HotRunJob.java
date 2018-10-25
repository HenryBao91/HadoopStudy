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
		
		Job job = Job.getInstance(conf, "Hot Sort");
		
		job.setJarByClass(HotRunJob.class);
		job.setMapperClass(HotMapper.class);	
		job.setReducerClass(HotReducer.class);
		
		// Map 输出格式设置
	    job.setMapOutputKeyClass(keyPair.class);
	    job.setMapOutputValueClass(Text.class);
		
		// TODO: specify output types
	    job.setNumReduceTasks(6);   // 设置reduce数量，要和年份个数保持一致
	    job.setPartitionerClass(FirstPartition.class);
	    job.setSortComparatorClass(SortHot.class);
	    job.setGroupingComparatorClass(GroupHot.class);
	    
		// TODO: specify input and output DIRECTORIES (not files)
	    FileInputFormat.addInputPath(job, new Path("/input/hot"));   
	    FileOutputFormat.setOutputPath(job, new Path("/output/hot"));

		if (!job.waitForCompletion(true))
			return;
	}

}
