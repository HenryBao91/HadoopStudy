package Demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FirstJob {

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "FirstJob");
		
		job.setJarByClass(Demo.FirstJob.class);
		job.setPartitionerClass(Demo.FirstPartition.class);
		job.setMapperClass(Demo.FirstMapper.class);
		job.setCombinerClass(Demo.FirstReducer.class);
		job.setReducerClass(Demo.FirstReducer.class);

		// TODO: specify output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// …Ë÷√Reduce ˝¡ø
		job.setNumReduceTasks(4);
	
		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, new Path("/input/userPushData"));
		FileOutputFormat.setOutputPath(job, new Path("output/weibo/output1"));

		if (!job.waitForCompletion(true))
			return;
	}

}
