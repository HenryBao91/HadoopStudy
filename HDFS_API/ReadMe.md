一、获取FileSystem实例的静态方法
public static FileSystem.get(Configuration conf) 
public static FileSystem.get(URI uri, Configuration conf) 
public static FileSystem.get(URI uri, Configuration conf, String user) 
（1）第一种，返回一个默认的文件系统，是在core-site.xml中通过fs.defaultFS来指定的。如果在fs.defaultFS没有设置，则返回本地文件系统。
（2）第二种，通过url指定要返回的文件系统。如果url是以hdfs标识开头，那么返回一个HDFS文件系统；如果没有，则返回本地文件系统。
（3）第三种，同（2），同时又限定了该文件系统的用户。
二、方法介绍
1、列举文件夹：FileStatus
最简单的方法是FileStatus(Path f)，使用其得到一个文件列表，通过遍历该文件列表，判断列表中的元素是不是一个文件夹，若是，则打印出文件夹的名称。