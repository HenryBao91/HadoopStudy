package temper;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupHot extends WritableComparator{

	// ���췽������
	public GroupHot() {
		super(temperatureRank.class , true) ;
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		temperatureRank o1 = (temperatureRank) a ;
		temperatureRank o2 = (temperatureRank) b ;
		// ��������
		return Integer.compare(o1.getYear(), o2.getYear());
		}

}
