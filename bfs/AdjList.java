package bfs;
import java.util.HashMap;

public class AdjList {
	
	//Global List for Adjacency List for all the nodes.
	public static final HashMap<String, String> map = new HashMap<>();
	private static AdjList instance = new AdjList();
	public AdjList() {

	}
	
	//Returns instance
	 public static AdjList getInstance() {
	        return instance;
	    }
	 
	 //Returns Value for the key
	 public static String getValue(String key) {
	        return map.get(key);
	    }

	 //Adds keys value of 2 dimensional arrays
	    public static void add(String[][] pairs) {
	        for(String[] pair : pairs) {
	            map.put(pair[0], pair[1]);
	        }
	    }
	    
	    //Adds one key value pair to hash map
	    public static void addonemap(String key, String value) {
	            map.put(key, value);
	    }
	    
	    //Adds an array of keys and values to hash map
	    public static void add(String[] keys, String[] values) {
	        for (int i = 0; i < keys.length; ++i) {
	            map.put(keys[i], values[i]);
	        }
	    }
}
