package temper;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortHot extends WritableComparator{

	// ���췽������
	public SortHot() {
		super(temperatureRank.class , true) ;
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		temperatureRank o1 = (temperatureRank) a ;
		temperatureRank o2 = (temperatureRank) b ;
		// ��������
		int res = Integer.compare(o1.getYear(), o2.getYear());
		// ��ݲ���ͬʱ������res
		if (res != 0) {
			return res ;
		}
		// ��������integerǰ��-��
		return -Integer.compare(o1.getHot(), o2.getHot()) ;		
	}

}
