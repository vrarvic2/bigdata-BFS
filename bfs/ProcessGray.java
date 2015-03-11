package bfs;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;

import bfs.Node.color;

public class ProcessGray {

	public OutputCollector<Text, Text> generateNodes(Node node,OutputCollector<Text, Text> output) throws IOException {
		
		//generating new nodes for each adjacent nodes
		for(String nodes:node.getAdjNodes()){
			if(!nodes.equals("")){
				int newDist = Integer.MAX_VALUE;
				if( !"Integer.MAX_VALUE".equalsIgnoreCase(node.getDistance()) )
					newDist = Integer.valueOf(node.getDistance());
				
				String newNodeString = node.getNewNodeString(nodes, String.valueOf(++newDist),node.getColor().toString(), node.getMyNode()).toString();
				String[] newNode = newNodeString.split("\t");
				
				output.collect(new Text(newNode[0]), new Text(newNode[1]));
			}
		}
		
		//Assigning the color of gray nodes to black
		node.setColor(color.BLACK);
		Counters.counters--;
		String sourceNodeString = node.getNewNodeString(node.getMyNode(), node.getDistance(),node.getColor().toString(), node.getSource()).toString();
		String[] newNode = sourceNodeString.split("\t");
		
		output.collect(new Text(newNode[0]), new Text(newNode[1]));
		return output;
	}
}
