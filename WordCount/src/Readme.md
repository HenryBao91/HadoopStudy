 һ��     StringTokenizerѧϰ
 StringTokenizerʹ�ã�����ͨsplit�������ֹ��캯����
       	public StringTokenizer(String str,String delim,boolean returnDelims)
	    public StringTokenizer(String str,String delim)
	    public StringTokenizer(String str)	
    ������ 
	str - Ҫ�������ַ����� 
	delim - �ָ����� 
	returnDelims - ָʾ�Ƿ񽫷ָ�����Ϊ��Ƿ��صı�־��
     ���У�������ʱ��delimΪĬ��ֵ�����ӣ�        
    String nameStr="Harry James Potter";
   //��Ҫ��ֵ��ַ����빹�캯���У�����һ��token����
   StringTokenizer strToken=new StringTokenizer(nameStr);
   //���в�ֵ����ַ���ʱ���������ַ���
   while(strToken.hasMoreTokens()){
      System.out.println(strToken.nextToken());
   }
    ��������
    Harry
	James
	Potter         
     
     
     
������ʽ��
 	BooleanWritable����׼��������ֵ
    ByteWritable�����ֽ���ֵ
    DoubleWritable��˫�ֽ���
    FloatWritable��������
    IntWritable��������
    LongWritable����������
    Text��ʹ��UTF8��ʽ�洢���ı�
    NullWritable����<key,value>�е�key��valueΪ��ʱʹ��     
    
    
    