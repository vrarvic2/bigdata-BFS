package bfs;

public class CompareNodes {

	public CompareNodes(Node reducedNode) {	
	}

	//Compares and updates the least distance
	public String compareDistance(Node reducedNode,Node tempNode){
		String reducedDistance = reducedNode.getDistance();
		String tempDistance = tempNode.getDistance();
		int redDist = Integer.MAX_VALUE;
		
		if(!reducedDistance.equalsIgnoreCase("Integer.MAX_VALUE")){
			 redDist =  returnInt(reducedDistance);	 
		}
		
		
		if(!tempDistance.equalsIgnoreCase("Integer.MAX_VALUE")){
			int tempDist = returnInt(tempDistance);
			if(redDist<tempDist){
				return returnStr(redDist);
			}
			else{
				reducedNode.setSource(tempNode.getSource());
				return returnStr(tempDist);
			}
		}
		else
			return returnStr(redDist);
	}
	
	//Compares and updates the darkest color
	public void compareColor(Node reducedNode,Node temp){
		if(reducedNode.getColor().ordinal()<temp.getColor().ordinal())
			reducedNode.setColor(temp.getColor());		
	}
	
	
	public int returnInt(String s){
		return Integer.parseInt(s); 
	}
	
	public String returnStr(int i){
		return String.valueOf(i);
	}
	
	
	
	
}
