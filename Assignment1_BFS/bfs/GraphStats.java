package bfs;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//This class generates graph stats
public class GraphStats {

	
	public GraphStats() {
	
	}

	  final static String FILE_NAME = "/Users/vravic2/Documents/workspace/Assignment_1/input/file.txt";
	  final static String OUTPUT_FILE_NAME = "/Users/vravic2/Documents/workspace/Assignment_1/graph_stats.txt";
	  final static Charset ENCODING = StandardCharsets.UTF_8;
	  
	  public static void calcGraph() throws IOException{
		  GraphStats text = new GraphStats();
		  text.computeStats();
	  
	  }

	  
public void computeStats() throws IOException{
	
	int totalNodes=0,totalEdges = 0;
	//Get the nodes and the adjacency List i.e hash map key and AdjList
	List<String> newlines = new ArrayList<String>(); 
	for(String name:AdjList.map.keySet()){
		String key = name.toString();
		String value = AdjList.map.get(key);
		newlines.add("My node : "+key+"\tAdj List : "+value);
		totalNodes++;
		totalEdges  = totalEdges + value.split(",").length;
	}
	
	this.writeLargerTextFile(OUTPUT_FILE_NAME, newlines,totalNodes,totalEdges/2);
}
	  //Writing in file
	  void writeLargerTextFile(String aFileName, List<String> aLines,int totalNodes,int edgeCount) throws IOException {
	    Path path = Paths.get(aFileName);
	    BufferedWriter writer = Files.newBufferedWriter(path, ENCODING);
	    //The content to write the file.
	    for(String line : aLines){
	    	writer.write(line);
	        writer.newLine();
	      }
	    writer.newLine();
	    writer.write("------------------------"); writer.newLine();
	    writer.write("No of Nodes : "+totalNodes);writer.newLine();
	    writer.write("No of Edges : "+edgeCount);writer.newLine();
	    
	    writer.close();
	  }
}
