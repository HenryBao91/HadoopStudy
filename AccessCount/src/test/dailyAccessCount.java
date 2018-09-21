/**
 * 
 */
package test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class dailyAccessCount {

	public static void main(String[] args) throws Exception{
		//实例化Configuration,初始化相关Hadoop配置
		Configuration conf = new Configuration() ;
		
	    //新建job并初始化，设置主类，设置一个用户定义的job任务名
		Job job = Job.getInstance(conf , "Daily Access Count") ;
			
		// 设置要生成Jar包的class
		job.setJarByClass(dailyAccessCount.class);
		
		// 为job设置Mapper类
		job.setMapperClass(MyMapper.class);
		// 为job设置Reducer类
		job.setReducerClass(MyReducer.class);
		
		/** 设置MapReduce输入输出键值对 */
		// 为job设置Map的输出key、value类
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		// 为job设置输出key、value类
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 读取数据文件输入路径
	    for (int i = 0; i < args.length - 1; ++i) {
	        FileInputFormat.addInputPath(job, new Path(args[i]));
	      }
		// 设置程序输出路径
	    FileOutputFormat.setOutputPath(job, new Path(args[args.length-1]));
		
		// 提交MapReduce任务允许，固定写法，并等待任务运行结束
	    System.exit(job.waitForCompletion(true) ? 0 : 1);  // 如果job运行成果，程序正常退出
	}

}
