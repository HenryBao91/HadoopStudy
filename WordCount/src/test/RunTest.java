/*
 *	说明：
 *  MapReduce_MR本地运行模式 
 */
package test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//选中多行，按ctrl+/ ，快速注释多行，再按一次取消注释	 
public class RunTest {

	public static void main(String[] args) throws Exception{
		
		//实例化Configuration,初始化相关Hadoop配置
		//这个类主要读取MapReduce系统配置信息hdfs-site.xml等。
	    Configuration conf = new Configuration();  
   	   
	    //新建job并初始化，设置主类，设置一个用户定义的job任务名
	    Job job = Job.getInstance(conf, "word count"); 
	    //装载编好的程序，设置生成Jar包的class
	    job.setJarByClass(RunTest.class);        
	     
	    //设置Mapper、Combiner、Reducer，固定写法，
	    //其中，Mapper、Reducer是必须设置的类，Combiner是可选项  
	    //为job设置Mapper类
	    job.setMapperClass(MapTest.class);        
	    //为job设置Combiner类
	    job.setCombinerClass(ReduceTest.class);  
	    //为job设置Reducer类
	    job.setReducerClass(ReduceTest.class);  
	     
	    // 设置MapReduce输入输出键值对格式
	    //为job的输出数据设置Key类，key设置为text类型，相当于java中的string类型
	    job.setOutputKeyClass(Text.class);         
	    //为job输出设置value类，value设置为IntWritable，相当于java中的int类型
	    job.setOutputValueClass(IntWritable.class); 
	    
	    // 设置输入与输出路径，还可以增加对输入与输出文件格式的设置
	    FileInputFormat.setInputPaths(job, new Path("d:/mr_input")); 
	    FileOutputFormat.setOutputPath(job, new Path("d:/mr_output")); 

	    // 提交MapReduce任务允许，固定写法，并等待任务运行结束
	    System.exit(job.waitForCompletion(true) ? 0 : 1);  // 如果job运行成果，程序正常退出
	   }
	
}


