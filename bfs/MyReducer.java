package bfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MyReducer extends MapReduceBase implements Reducer<Text,Text,Text,Text> {

	
	@Override
	public void reduce(Text key, Iterator<Text> value,
			OutputCollector<Text, Text> output, Reporter r)
			throws IOException {
		
			//creating array list of objects to compare in node representation
			ArrayList<Node> myAl = new ArrayList<Node>();
			Node reducedNode=null;
			CompareNodes compare=null;
			String adjString =null;
			
			int count=0;
			while(value.hasNext()){
				 String s = value.next().toString();
				 
				 StringBuffer strbuf = new StringBuffer(key.toString());
				 strbuf.append("\t"+s);
				 if(++count==1)
				 {
					reducedNode = new Node(key.toString()+"\t"+s);
					compare = new CompareNodes(reducedNode);
					adjString = reducedNode.getAdjString(key.toString());
				 }
				 Node node = new Node(strbuf.toString());
				 if(!node.getAdjString(node.getMyNode()).equals("null")){
					 adjString =  node.getAdjString(node.getMyNode());
				 }
				 myAl.add(node); 
			}
		
			for(Node temp:myAl){	
				//Compare all the nodes and get the one with the least distance and darkest color
				String reducedDist = compare.compareDistance(reducedNode,temp);
				//Updating a reduced node.
				reducedNode.setDistance(reducedDist);

				compare.compareColor(reducedNode,temp);
			}
		
			String val = reducedNode.getCurrentNodeString(adjString).toString();
			
			//modify counters value
			if(reducedNode.getColor().toString().equalsIgnoreCase("GRAY")){
				Counters.counters++;
			}
		output.collect(new Text(key.toString()), new Text(val));	
	}
}
