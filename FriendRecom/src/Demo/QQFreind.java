package Demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class QQFreind {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "QQFreindRecommend");
		job.setJarByClass(Demo.QQFreind.class);
		
		job.setMapperClass(Demo.MyMapper.class);
		job.setReducerClass(Demo.MyReducer.class);

		// TODO:设置key/value输出格式
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// TODO: 设置输入/输出路径,mapreduce输入数据所在路径或文件
		FileInputFormat.setInputPaths(job, new Path("/input/QQ/testData"));
		FileOutputFormat.setOutputPath(job, new Path("/output/QQ"));

		if (!job.waitForCompletion(true))
			return;
	}

}
