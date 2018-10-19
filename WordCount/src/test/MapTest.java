package test;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**	说明：
 * Mapper共有3个函数，分别是setup、map、cleanup 。
 * 1、setup：Mapper任务启动之后首先执行setup函数，该函数主要用于初始化工作；
 * 2、map：map函数针对每条输入键值对执行函数中定义的逻辑处理，并按规定的键值对格式输出；
 * 3、cleanup：在所有键值对处理完成后，再调用cleanup函数，其主要用于关闭资源等操作。
 * */

/** 自定义Map函数：覆盖map函数：继承Mapper类并重写map方法
 * @param KEYIN
 *            →k1 表示每一行的起始位置（偏移量offset）
 * @param VALUEIN
 *            →v1 表示每一行的文本内容
 * @param KEYOUT
 *            →k2 表示每一行中的每个单词
 * @param VALUEOUT
 *            →v2 表示每一行中的每个单词的出现次数，固定值为1
 */

/*从代码中可以看出，在Mapper类和Reducer类中都使用了Hadoop自带的基本数据类型，
 * 例如String对应Text，long对应LongWritable，int对应IntWritable。
 * 这是因为HDFS涉及到序列化的问题，Hadoop的基本数据类型都实现了一个Writable接口，
 * 而实现了这个接口的类型都支持序列化。
 * */

// 自定义Mapper模块类名MapTest，需要继承Mapper，同时需要设置输入/输出键值对格式
	// 其中，键值对格式要和输入格式设置的类读取生成的键值对格式匹配
	// 输出键值对格式需要和Driver模块中设置的Mapper输出的键值对格式匹配，即Text、IntWritable
public class MapTest extends Mapper<Object, Text, Text, IntWritable>{

	//one是常量，不管一个单词回出现多少次，只要出现就计算1次
	private final static IntWritable one = new IntWritable(1); 
	
	//存放单词的变量
    private Text word = new Text(); 
     // 每次调用map方法都会传入split中一行数据，key：该行数据所在文件位置的下标，value：这行数据   
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
     /**
      * map函数的代码实现要和实际业务逻辑关联，改程序业务需要是词频统计
      * 处理逻辑时把每个输入键值对（键值对组成为<行的偏移量，行字符串>）的值(也就是字符串)
      * 按照分隔符分割，得到每个单词，然后输出每个单词和1组成的键值对
      * */	
    	
      //Java的字符串分解类，默认分隔符“空格”、“制表符(‘\t’)”、“换行符(‘\n’)”、“回车符(‘\r’)”  
      // itr属于一个token对象
      StringTokenizer itr = new StringTokenizer(value.toString());  
      
      //循环条件表示返回是否还有分隔符
      //当有拆分的子字符串时，进入while循环
      while (itr.hasMoreTokens()) {  
    	// nextToken()：返回从当前位置到下一个分隔符的字符串
    	//word.set()：Java数据类型与hadoop数据类型转换  
        word.set(itr.nextToken());   
        //将一个单词写入的时候，值永远是1
        context.write(word, one); 
      }
    }
	
}
