 一、     StringTokenizer学习
 StringTokenizer使用，作用通split，有三种构造函数：
       	public StringTokenizer(String str,String delim,boolean returnDelims)
	    public StringTokenizer(String str,String delim)
	    public StringTokenizer(String str)	
    参数： 
	str - 要解析的字符串。 
	delim - 分隔符。 
	returnDelims - 指示是否将分隔符作为标记返回的标志。
     其中，单参数时，delim为默认值，例子：        
    String nameStr="Harry James Potter";
   //将要拆分的字符传入构造函数中，生成一个token对象
   StringTokenizer strToken=new StringTokenizer(nameStr);
   //当有拆分的子字符串时，输出这个字符串
   while(strToken.hasMoreTokens()){
      System.out.println(strToken.nextToken());
   }
    输出结果：
    Harry
	James
	Potter         
     
     
     
二、格式类
 	BooleanWritable：标准布尔型数值
    ByteWritable：单字节数值
    DoubleWritable：双字节数
    FloatWritable：浮点数
    IntWritable：整型数
    LongWritable：长整型数
    Text：使用UTF8格式存储的文本
    NullWritable：当<key,value>中的key或value为空时使用     
    
    
    