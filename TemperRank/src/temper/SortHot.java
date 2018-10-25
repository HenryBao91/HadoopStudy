package temper;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortHot extends WritableComparator{

	public SortHot() {
		// 传递keyPair.class继承WritableComparator
		// true:表示是否创建实例
		super(keyPair.class , true) ;
	}

	@Override
	// 传递过来两个对象进行比较，这两个对象是keyPair对象
	public int compare(WritableComparable a, WritableComparable b) {
		keyPair o1 = (keyPair) a ;
		keyPair o2 = (keyPair) b ;
		// 先比较年份，比较默认是升序排序
		int res = Integer.compare(o1.getYear(), o2.getYear());
		if (res != 0) {
			return res ;
		}
		// 降序排序
		return -Integer.compare(o1.getHot(), o2.getHot()) ;		
	}

}
