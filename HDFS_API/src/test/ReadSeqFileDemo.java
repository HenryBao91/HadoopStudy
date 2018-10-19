/**
 * 读取序列化文件，将读取的数据写入到本地文件系统janfeb.txt
 * @author hongzhen
 */
package test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

public class ReadSeqFileDemo {

	public static void main(String[] args) throws IOException {
		
		// 获取配置
		Configuration conf = new Configuration() ;
		conf.set("fs.defaultFS", "server01:9000");

		// 获取文件系统
		FileSystem fs = FileSystem.get(conf) ;
		
		// 如果使用了使用@Deprecated注释的方法，编译器将出现警告信息。使用这个注释将警告信息去掉。
		@SuppressWarnings("deprecation")
		// 获取SequenceFile对象
		SequenceFile.Reader reader=new SequenceFile.Reader
				(fs, new Path("/output/SelectDataSeq/part-m-00000"), conf);	
		
		// 获取序列化文件中使用的键值类型
		Text key = new Text();
		Text value = new Text();
		
		// 读取的数据写入janfeb.txt文件
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("F:\\TestFiles\\janfeb.txt",true))) ;
		while(reader.next(key,value)) {
			out.write(key.toString() + "\t" + value.toString() + "\r\n" );
		}
		out.close();
		reader.close();
		
		System.out.println("Code Over");
	}
}
