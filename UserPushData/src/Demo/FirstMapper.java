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
 * 	1��ͳ��ÿ�����ڸ���΢���г��ֵĴ������� TF ��
 *  2��ͳ��΢��������
 * */
public class FirstMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void map(LongWritable ikey, Text ivalue, Context context) 
			throws IOException, InterruptedException {
		String[] v = ivalue.toString().trim().split("\t") ;
		if (v.length >= 2) {
			// ȡid��΢������
			String id = v[0].trim() ; 
			String content = v[1].trim() ;
			
			// ΢��������Ҫ������΢�����ݱ��һ������
			StringReader sReader = new StringReader(content) ;
			IKSegmenter ikSegmenter = new IKSegmenter(sReader, true);
			Lexeme word = null ;
			while ( (word = ikSegmenter.next()) != null) {
				String w = word.getLexemeText() ;
				// ͳ��TF
				context.write(new Text(w +"_" + id), new IntWritable(1) );	
			}
			// ͳ��N��΢���������� 
			// ֻҪ����һ��΢���������һ��keyΪcount��valueֵΪ1������
			context.write(new Text("count "), new IntWritable(1) );	
		}else {
			System.out.println(ivalue.toString() +"------------" );
		}
	}

}
