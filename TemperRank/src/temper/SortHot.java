package temper;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortHot extends WritableComparator{

	// 构造方法函数
	public SortHot() {
		super(temperatureRank.class , true) ;
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		temperatureRank o1 = (temperatureRank) a ;
		temperatureRank o2 = (temperatureRank) b ;
		// 升序排序
		int res = Integer.compare(o1.getYear(), o2.getYear());
		// 年份不相同时，返回res
		if (res != 0) {
			return res ;
		}
		// 降序排序，integer前加-号
		return -Integer.compare(o1.getHot(), o2.getHot()) ;		
	}

}
