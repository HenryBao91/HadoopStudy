package Demo;

import java.io.IOException;
import java.io.StringReader;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;


/**
 * FirstMapper: 
 * 	1、统计每个词在该条微博中出现的次数，即 TF ；
 *  2、统计微博总条数
 * */
public class FirstMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void map(LongWritable ikey, Text ivalue, Context context) 
			throws IOException, InterruptedException {
		String[] v = ivalue.toString().trim().split("\t") ;
		if (v.length >= 2) {
			// 取id和微博内容
			String id = v[0].trim() ; 
			String content = v[1].trim() ;
			
			// 微博内容需要处理，把微博内容变成一个个词
			StringReader sReader = new StringReader(content) ;
			IKSegmenter ikSegmenter = new IKSegmenter(sReader, true);
			Lexeme word = null ;
			while ( (word = ikSegmenter.next()) != null) {
				String w = word.getLexemeText() ;
				// 统计TF
				context.write(new Text(w +"_" + id), new IntWritable(1) );	
			}
			// 统计N（微博总条数） 
			// 只要出现一条微博，就输出一个key为count，value值为1的数据
			context.write(new Text("count "), new IntWritable(1) );	
		}else {
			System.out.println(ivalue.toString() +"------------" );
		}
	}

}
