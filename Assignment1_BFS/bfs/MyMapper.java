package bfs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import bfs.Node.color;



public class MyMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Writable values,
			OutputCollector<?, ?> output, Reporter reporter) throws IOException {
	}

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, Text> output, Reporter r) throws IOException {
		Node node = new Node(value.toString());

		//If the current node is the source node change the values of source node to start the process
		if( node.getMyNode().equalsIgnoreCase(MyDriver.setSource) && node.getColor().toString().equals("WHITE")){	
			node.setColor(color.GRAY);
			node.setDistance("0");
			node.setSource("source");
	}
		
		String s = value.toString();	
		
		// if the nodes are black or white just skip it
		if (node.getColor().toString().equalsIgnoreCase("BLACK")
				|| node.getColor().toString().equalsIgnoreCase("WHITE")) {
			if (s.length() > 0) {
				String[] sameNode = s.split("\t");
				output.collect(new Text(sameNode[0]), new Text(sameNode[1]));
			}
		}
		
		// processing gray nodes
		else {
			ProcessGray gray = new ProcessGray();
			gray.generateNodes(node, output);
		}

	}

}
