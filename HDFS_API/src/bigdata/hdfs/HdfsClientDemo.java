package bigdata.hdfs;

import java.net.URI;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;


/**
 * 
 * 客户端去操作hdfs时，是有一个用户身份的
 * 默认情况下，hdfs客户端api会从jvm中获取一个参数来作为自己的用户身份：-DHADOOP_USER_NAME=hadoop
 * 
 * 也可以在构造客户端fs对象时，通过参数传递进去
 * @author
 *
 */
public class HdfsClientDemo {
	// 定义初始化配置
/*	FileSystem fs = null;
	Configuration conf = null;*/
	
/*	
	@Test
	public void testUpload() throws Exception {
		
		Thread.sleep(2000);  
		fs.copyFromLocalFile(new Path("F:/Hadoop/log/access.log"), new Path("/mylogs/access.log.copy"));
		fs.close();          // src path指windows路径  , dst path指HDFS路径
	}
	
	@Test
	public void testDownload() throws Exception {
		
		fs.copyToLocalFile(new Path("/access.log.copy"), new Path("d:/"));
		fs.close();
	}
	
	@Test
	public void testConf(){
		Iterator<Entry<String, String>> iterator = conf.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getValue() + "--" + entry.getValue());//conf加载的内容
		}
	}
	
	*//*** 创建目录  ***//*
	@Test
	public void makdirTest() throws Exception {
		boolean mkdirs = fs.mkdirs(new Path("/aaa/bbb"));
		System.out.println(mkdirs);
	}
	
	*//*** 删除	 ***//*
	@Test
	public void deleteTest() throws Exception{
		boolean delete = fs.delete(new Path("/aaa"), true);//true， 递归删除
		System.out.println(delete);
	}
	
	@Test
	public void listTest() throws Exception{
		
		FileStatus[] listStatus = fs.listStatus(new Path("/"));
		for (FileStatus fileStatus : listStatus) {
			System.err.println(fileStatus.getPath()+"================="+fileStatus.toString());
		}
		//会递归找到所有的文件
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
		while(listFiles.hasNext()){
			LocatedFileStatus next = listFiles.next();
			String name = next.getPath().getName();
			Path path = next.getPath();
			System.out.println(name + "---" + path.toString());
		}
	}*/
	
	
	public static void ListDirOrFile() throws Exception {
		
		// 获取配置
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://server01:9000");
		
		//拿到一个文件系统操作的客户端实例对象
		FileSystem fs = FileSystem.get(conf);
		// 声明文件路径
		Path path = new Path("/output") ;
		// 获取文件列表
		FileStatus[] fileStatuses = fs.listStatus(path) ;
		// 遍历文件列表
		for (FileStatus file : fileStatuses) {
			// 判断是否是文件
			//if (file.isFile()) {
			// 判断是否是文件夹
			if (file.isDirectory()) {
				System.out.println(file.getPath().toString());
			}
		}
		// 关闭文件系统
		fs.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		ListDirOrFile() ;

	}
}
















