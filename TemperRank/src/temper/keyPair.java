package temper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * 由于数据复杂，所以需要自定义封装数据类型
 * Map输出的key，即为该封装对象
 * Hadoop数据传递采用RPC协议，RPC协议是一种二进制数据传递方式
 * */
public class keyPair implements WritableComparable<keyPair>{

	private int year ; 
	private int hot ;
	// 定义set、get方法
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
	@Override
	// readFiles可以理解为一个反序列化的过程
	public void readFields(DataInput in) throws IOException {
		
		this.year = in.readInt();
		this.hot = in.readInt() ;
	}
	// write可以理解为一个序列化过程，将对象变成二进制数据
	public void write(DataOutput out) throws IOException {
		out.writeInt(year);
		out.writeInt(hot);
	}
	/*	compareTo：比较的作用
	 *  other是指传过来的对象，和当前对象作比较
	 * */ 
	public int compareTo(keyPair other) {
		int result = Integer.compare(year, other.getYear()) ;
		if (result != 0) { // 即年份不相等
			return result;
		}
		// 如果年份相同，再对hot进行比较
		return Integer.compare(hot, other.getHot());
	}
	
	@Override
	public String toString() {
		return year+"\t"+hot;
	}
	@Override
	public int hashCode() {
		return new Integer(year + hot).hashCode();
	}
}
