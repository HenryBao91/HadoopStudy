package test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// Driver 模块
// 点击File->new，新建MapReducerDriver模块，下发选项选择Map和Reduce模块，
// 自动生成基础配置代码
public class accessTimeSort {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "accessTimeSort");
		job.setJarByClass(test.accessTimeSort.class);
		
		job.setMapperClass(test.MyMapper.class);
		job.setReducerClass(test.MyReducer.class);

		/** 设置MapReduce输入输出键值对 */
		// 为job设置Map的输出key、value类
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		// 为job设置输出key、value类
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		

/**  		两种设置路径方式：1、程序设置 ；   2、运行时手动输入	 	**/
		// TODO: 设置输入输出路径
		FileInputFormat.setInputPaths(job, new Path("/output/AccessCount"));
		FileOutputFormat.setOutputPath(job, new Path("/output/TimeSort2"));

		// 输入路径方式，读取数据文件输入路径
		/*	    for (int i = 0; i < args.length - 1; ++i) {
	        FileInputFormat.addInputPath(job, new Path(args[i]));
	      }
		// 设置程序输出路径
	    FileOutputFormat.setOutputPath(job, new Path(args[args.length-1]));
		*/
		
		if (!job.waitForCompletion(true))
			return;
	}

}
