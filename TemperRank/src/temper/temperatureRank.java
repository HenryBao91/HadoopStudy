package temper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.directory.shared.kerberos.components.HostAddress;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class temperatureRank implements WritableComparable<temperatureRank> {

	private int year;
	private int hot;
	
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
	public void readFields(DataInput in) throws IOException {
		this.hot  = in.readInt();
		this.year = in.readInt();
	}

	public void write(DataOutput out) throws IOException {
		out.writeInt(year);
		out.writeInt(hot);
	}
	
	public int compareTo(temperatureRank o) {
		// o是传过来的对象，和当前对象year进行比较
		int res = Integer.compare( year, o.getYear());
		// 年份不同时,返回比较结果
		if(res != 0){
			return res ;
		}
		// 年份相同时，返回到温度里进行比较
		return Integer.compare(hot, o.getHot());
	}

	@Override 
	public String toString() {
		// 重写toString()方法
		return year+"\t"+hot ;
	}
	
	@Override
	public int hashCode() {
		// 重写hashCode()方法
		return new Integer(year + hot).hashCode();
	}
}
