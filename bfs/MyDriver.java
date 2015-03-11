package bfs;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;

public class MyDriver {
	
	public static String setSource;

	public static void main(String[] args) throws IOException {
		int iterationCount =	0;	//	counter	to	set	the	ordinal	number	of	the	intermediate	outputs	
		Counters.counters=1;
		while(Counters.counters!=0){
			
			JobClient client = new JobClient();
			JobConf conf = new JobConf(bfs.MyDriver.class);
			
			//set the mapper and reducer class
			conf.setMapperClass(bfs.MyMapper.class);
			conf.setReducerClass(bfs.MyReducer.class);		
			conf.setPartitionerClass(bfs.GraphPartitioner.class);
			
			//Set the output types for mapper and reducer Class
			conf.setMapOutputKeyClass(Text.class);
			conf.setMapOutputValueClass(Text.class);
			conf.setOutputKeyClass(Text.class);
			conf.setOutputValueClass(Text.class);
			
			//creating more than 1 output folder
			String input, output;	
			if	(iterationCount == 0)	//the first itera1on the input will be the first input argument	
				input = args[0];	
			else	
				input = args[1]	+ iterationCount;	
			
			output = args[1]	+ (iterationCount + 1);	// send the output file	
			
			//This will get the source ndoe from the user
			setSource = args[2];
			
			//Set the input and output file path.
			FileInputFormat.setInputPaths(conf, new Path(input));
			FileOutputFormat.setOutputPath(conf, new Path(output));

			iterationCount++;
			client.setConf(conf);
			
			//JOB 1
			try {
				JobClient.runJob(conf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//JOB 2
		//Calling the graph stats class to generate stats to the file graph_stats.txt
		GraphStats.calcGraph();
		}

}
