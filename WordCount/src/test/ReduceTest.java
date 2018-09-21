package test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**	说明：
 * Reducer共有3个函数，分别是setup、reducer、cleanup 。
 * 1、3、：setup、cleanup和Mapper中的作用相同；
 * 2、reducer：reducer针对相同键，把其键值全部累加起来，最后输出结果
 * */

//自定义Reducer模块类名ReduceTest，需要继承Reducer，同时需要设置输入/输出键值对格式
	// 其中，输入键值对格式要和Mapper的输出键值对格式保持一致
	// 输出键值对格式需要和Driver模块中设置的Reducer输出的键值对格式匹配
public class ReduceTest extends Reducer<Text,IntWritable, 	// 输入键值对格式
										Text,IntWritable>{  // 输出键值对格式

	private IntWritable result = new IntWritable();
	
	//Context 类是Mapper 类的内部抽象类，它实现了MapContext 接口MapContext 里面可以得到split的信息，这个接口实现了 TaskInputOutputContext 这个接口
    public void reduce(Text key, Iterable<IntWritable> values,   
                       Context context
                       ) throws IOException, InterruptedException {
                       
      int sum = 0;
      //循环values，并记录单词个数
      for (IntWritable val : values) {   
        sum += val.get();
      }
    
      //Java数据类型sum，转换为hadoop数据类型result
      result.set(sum);    
      //输出结果到hdfs
      context.write(key, result);   
    }   
}


