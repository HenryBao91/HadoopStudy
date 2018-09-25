package SubmitDemo;
/**
 * 驱动类使用Submit
 * Eclipse直接提交作业job
 * */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileAsTextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class LogCount extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		// 参数数组
		String[] myArgs = {
				"/output/SelectDataSeq" ,
				"/output/LogCntToolRunner"
		};
		try {
			// 作业配置
			ToolRunner.run(new Configuration(), new LogCount(), myArgs) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "logcountTool");
		
		// 设置主类和Mapper、Reducer类
		job.setJarByClass(LogCount.class);
		job.setMapperClass(LogCountMapper.class);
		job.setReducerClass(LogCountReducer.class);

		// 设置Combiner、Partitioner、NumReducer个数
		job.setCombinerClass(LogCountCombiner.class);
		job.setPartitionerClass(LogCountPartioner.class);
		job.setNumReduceTasks(2);
		
		// TODO: 设置输出key、value类型
		job.setOutputKeyClass(MemberLogTime.class);
		job.setOutputValueClass(IntWritable.class);

		// 设置输入、输出格式
		job.setInputFormatClass(SequenceFileAsTextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// TODO: 设置输入、输出路径
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileSystem.get(conf).delete(new Path(args[1]), true);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		return job.waitForCompletion(true)? -1 : 1 ;
	}
}
