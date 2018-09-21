package Demo;
/**
 * 程序任务实现步骤： ReadMe.md
 * */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileAsTextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class LogCount {

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "LogCounterDemo");
		
		// 设置主类和Mapper、Reducer类
		job.setJarByClass(Demo.LogCount.class);
		job.setMapperClass(Demo.LogCountMapper.class);
		job.setReducerClass(Demo.LogCountReducer.class);

		// 设置Combiner、Partitioner、NumReducer个数
		job.setCombinerClass(LogCountCombiner.class);
		job.setPartitionerClass(LogCountPartioner.class);
		job.setNumReduceTasks(2);
		
		// TODO: specify output types
		job.setOutputKeyClass(MemberLogTime.class);
		job.setOutputValueClass(IntWritable.class);

		// 设置输入、输出格式
		job.setInputFormatClass(SequenceFileAsTextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileSystem.get(conf).delete(new Path(args[1]), true);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		if (!job.waitForCompletion(true))
			return;
	}

}
