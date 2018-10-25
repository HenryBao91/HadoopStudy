package temper;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupHot extends WritableComparator{

	// 传递keyPair类
	public GroupHot() {
		super(keyPair.class , true) ;
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		keyPair o1 = (keyPair) a ;
		keyPair o2 = (keyPair) b ;
		// 只需要比较年份
		return Integer.compare(o1.getYear(), o2.getYear());
		}

}
