/**
 * 自定义MemberLogTime类，实现接口WritableComparable
 */
package SubmitDemo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class MemberLogTime implements WritableComparable<MemberLogTime> {

	// 声明两个对象，分别为用户名和登录时间
	public String member_name ;
	private String logTime ;
	
	public MemberLogTime() {    //构造方法
	}
	
	public MemberLogTime(String member_name, String logTime ) {
		this.member_name = member_name ;
		this.logTime = logTime ;
	}
	
	//  获取用户名方法
	public String getMember_name() {
		return member_name;
	}
	//  设定用户名方法
	public void setMember_name(String member_name) {
		this.member_name = member_name ;
	}
	
	//  获取登录时间方法
	public String getLogTime() {
		return logTime;
	}
	//  设定登录时间方法
	public void setgetLogTime(String logTime) {
		this.logTime = logTime ;
	}
	
	@Override
	// 从in中反序列化该对象的字段
	public void readFields(DataInput in) throws IOException {
		// readUTF()方法：读取已使用修改后的UTF-8格式编码的字符串
		this.member_name = in.readUTF() ; 
		this.logTime = in.readUTF() ;
	}

	@Override
	// 将该对象的字段序列化到out中
	public void write(DataOutput out) throws IOException {
		// writeUTF()方法：根据字符串中每个字符的修改为UTF-8表示，
		// 将两个字节的长度信息写入输出流。
		out.writeUTF(member_name);
		out.writeUTF(logTime);
	}

	@Override
	public int compareTo(MemberLogTime o) {
		// compareTo()方法：根据用户名进行排序
		return this.getMember_name().compareTo(o.getMember_name());
	}

	@Override
	public String toString() {
		// 重写toString()方法，该方法返回用户名和登录时间的字符串格式
		return this.member_name + "," + this.logTime ;
	}
}
