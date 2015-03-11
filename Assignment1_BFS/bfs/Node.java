package bfs;

public class Node {

	static enum MoreIterations {
		numberOfIterations }
	
	private String myNode;
	
	private String distance;
	
	enum color {WHITE,GRAY,BLACK};
	
	private String source;
	
	private String currentNodeString;
	
	private color color;
	
	//Representation of nodes
	@SuppressWarnings("static-access")
	public Node(String nodeString) {
		
		//splitting the nodes with the <tab>
		this.setCurrentNodeString(nodeString);
		String[] parts = nodeString.split("\\|");
		String[] nodeList = parts[0].split("	");
		this.setMyNode(nodeList[0]);
		this.setDistance(parts[1]);
		this.setColor(this.getColor().valueOf(parts[2]));
		
		this.setSource(parts[3]);
		AdjList.addonemap(nodeList[0], nodeList[1].substring(0, nodeList[1].length()-1));
	}


	//Returns a new node string 
	public StringBuffer getNewNodeString(String newnode,String newDist,String newcolor,String newSource){
		StringBuffer newNodeString = new StringBuffer();
		
		newNodeString.append(newnode+"\t"+this.getAdjString(newnode)+",|"+newDist+"|"+color.toString()+"|"+newSource);
		return newNodeString;
	}
	
	//Returns current node String
	public StringBuffer getCurrentNodeString(String adjNode){
		StringBuffer currNodeString = new StringBuffer();
		currNodeString.append(adjNode+",|"+this.getDistance()+"|"+this.getColor().toString()+"|"+this.source);
		return currNodeString;
	}
	
	
	//Returns all the Adjacency nodes
	public String[] getAdjNodes(){
		return AdjList.map.get(this.myNode).split(",");
	}
	
	public String getAdjString(String anynode){		
		return AdjList.map.get(anynode);
	}
	
	
	
	//Getter and setter methods for all members for the node.
	public String getMyNode() {
		return myNode;
	}

	public void setMyNode(String myNode) {
		this.myNode = myNode;
	}
	
	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	public color getColor() {
		return color;
	}


	public void setColor(color color) {
		this.color = color;
	}


	public String getCurrentNodeString() {
		return currentNodeString;
	}


	public void setCurrentNodeString(String currentNodeString) {
		this.currentNodeString = currentNodeString;
	}

}
