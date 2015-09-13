package mazeGenerator;
import java.util.HashMap;
import java.util.Map.Entry;

public abstract class MazeGenerator {
	
	protected MazeNode[][] nodes;
	protected int mazeWidth;
	protected int mazeHeight;
	
	public abstract MazeNode[][] getMaze();
	
	
	
	public HashMap<Direction,MazeNode> getNeighbors(MazeNode node){
		int x = node.x;
		int y = node.y;
		
		HashMap<Direction,MazeNode> map = new HashMap<>();
		
		if(x != 0) map.put(Direction.W, nodes[x-1][y]);
        if(x < mazeWidth - 1) map.put(Direction.E, nodes[x+1][y]);
        if(y != 0) map.put(Direction.N, nodes[x][y-1]);
        if(y < mazeHeight - 1) map.put(Direction.S,nodes[x][y+1]);
        
        return map;
	}
	
	protected void initializeNodes(){
		nodes = new MazeNode[mazeWidth][mazeHeight];
        for(int x = 0; x<mazeWidth; x++){
            for(int y = 0; y<mazeHeight;y++){
                nodes[x][y] = new MazeNode(x,y);
            }
        }
	}
	
	public class HashEntry<K,V> implements Entry<K,V>{
		
		private K key;
		private V value;
		
		public HashEntry(K key, V value){
			this.key= key;
			this.value = value;
		}
		
		public K getKey(){
			return key;
		}
		
		public V getValue(){
			return value;
		}
		
		public V setValue(V newValue){
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
	}
}
