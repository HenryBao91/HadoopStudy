一、Combiner（非必需）
1、Combiner是一个运行在Map端的“迷你Reduce”过程，它只处理单台机器生成的数据。
2、声明Combiner继承的是Reducer，其方法实现原理和Reduce的实现原理基本相同。
3、使用条件：仅当Reducer输入的键值对类型和Reducer输出的键值对类型一样，并且
计算逻辑不影响最终计算结果时，才可以在MapReduce程序中加入Combiner。

二、Partioner（非必需）
1、Map或Combiner的输出结果会被Partioner均匀地分配到每个Reducer上，Reducer的
	输出结果又会通过OutputFormat解析成特定的格式存储到HDFS上。
2、Partioner组件的功能是让Map对key进行分区，从而将不同的key分发到不同的Reducer中
	以进行处理。
3、Partioner的数量等于Reducer的个数，Reducer的个数可以在驱动类里面通过
	job.setNumReduceTasks设置。
4、自定义Partioner需要继承Partioner<K2,V2>，并且重写getPartioner的实现方法。
* 如果最终结果要输出到多个文件里，那么只需要让getPartioner方法按照一定规则返回0,1,2,3即可。

三、自定义计数器
1、Hadoop自带计算器分类：
	MapReduce任务计数器、文件系统计数器、输入文件计数器、输出文件计数器、作业计数器。
2、自定义计数器有两种实现方式：
（1）、通过Java枚举（enum）类型来定义，一个作业可以定义的枚举类型数量不限，各枚举类型
	包含的字段数量也不限。枚举类型的名称即为组的名称，枚举类型的字段就是计数器的名称。
	使用getCounter()方法获取枚举中字段的值。
（2）、使用动态计数器。Context类中还有一个重载的方法:
	getCounter(String groupName,String countName)
	能够对当前计数器进行动态计数。
3、方法：首先，在Mapper类中定义枚举类型，接着在map函数里调用Context类的getCounter方法，
说明使用了枚举类型的哪个计数器，还需要调用increment()方法进行计数的添加。（计数器不是只能
在Mapper中添加，在Reducer中也可以添加。）


任务实现：
通过MapReduce编程实现用户在2016年1月和2月份每天登录次数统计。
（1）、读取HDFS上生成的序列化文件，该序列化文件对应的键值对为<Text,Text>.
（2）、MapReduce根据输入文件计算输入分片（Split），每个分片对应一个
	Map任务，而一个文件块一般对应一个分片。
（3）、InputFormat将输入分片解析成一个个的key/value对。
（4）、需要自定义一个建类型MemberLogTime来表示用户名和登录时间。
（5）、Map阶段读取InputFormat解析的键值对，该键值对类型必须是序列化文件对应的
	键值对类型，即<Text,Text>。map哈数将读取进来的键和值作为输出的键，类型为
	自定义的MemberLogTime，Map输出的值为1.由于需要统计输入的记录中1月份和2
	月份的记录分别是多少，所以需要在Mapper类中自定义一个计数器。
（6）、为减少Reducer复旦，提高程序运行效率，加入Combiner组件。
（7）、任务要求结果输出两个文件，分别存储1月和2月的输出结果，所以需要Partioner组件，
	并设置Reducer个数为2。
（8）、Reducer阶段根据Combiner传输过来的数据进行汇总统计，对相同键的值进行相加，
	相加结果作为值输出，输出的键仍是用户名和登录时间。并且在reduce函数里设置一个动态
	计数器，统计1月和2月分到输出记录数。

