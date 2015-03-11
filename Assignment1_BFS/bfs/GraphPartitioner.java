package bfs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

public class GraphPartitioner implements Partitioner<Text,Text> {

	@Override
	public void configure(JobConf arg0) {
	}
	
	//Partitioner from mapper to reducer
	@Override
	public int getPartition(Text key, Text value, int numPartitions) {
		
		int node = Integer.parseInt(key.toString());
		
		if(numPartitions==0)
			return 0;
		
		if(node<=20000)
			return 0;
		
		if(node>20000&&node<=30000)
			return 1%numPartitions;
		
		else
			return 2%numPartitions;
		
	}
}
