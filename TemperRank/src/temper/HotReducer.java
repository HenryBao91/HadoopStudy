package temper;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class HotReducer extends Reducer<keyPair, Text, keyPair, Text> {

	public void reduce(keyPair kp, Iterable<Text> values,
			Context context)
			throws IOException, InterruptedException {
		// process values
		for (Text val : values) {
			context.write(kp, val);
		}
	}

}
