package Demo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
		// process values
		// 创建Set，因为set可以去除其中重复的元素
		Set<String> set = new HashSet<String>() ;
		for (Text val : values) {
			set.add(val.toString()) ;
		}
		if (set.size() > 1) {
			for (Iterator i = set.iterator(); i.hasNext();) {
				// 得到一个好友名字
				String name = (String) i.next();
				for (Iterator j = set.iterator(); j.hasNext();) {
					// 得到另一个好友名字
					String other = (String) j.next();
					// 如果不是自己，即不相同才输出
					if (!name.equals(other)) {
						context.write(new Text(name), new Text(other));
					}
				}
			}
		}
	}

}
