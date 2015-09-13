package mazeGenerator.algorithms;

import mazeGenerator.*;
import java.util.*;



public class GrowingTree extends MazeGenerator{
	
	public NodeSelector nodeSelector;
	
	public GrowingTree(int width, int height){
		
		this(width,height,GrowingTree::halfAndHalf);
				
	}
	
	public GrowingTree(int width, int height, NodeSelector nodeSelector){
		
		this.mazeWidth = width;
		this.mazeHeight = height;
		this.nodeSelector= nodeSelector;
		
		this.initializeNodes();
		
		List<MazeNode> nodeList = new LinkedList<>();
		Random r = new Random();
		nodeList.add(nodes[r.nextInt(width)][r.nextInt(height)]);
		
		while(!nodeList.isEmpty()){
			MazeNode currentNode = nodeSelector.getNode(nodeList);
			currentNode.visited= true;
			HashMap<Direction,MazeNode> neighbors = this.getNeighbors(currentNode);
			List<HashEntry<Direction,MazeNode>> neighborList = new LinkedList<>();
			
			neighbors.forEach((d,n)->{
				if (!n.visited) neighborList.add(new HashEntry<>(d,n));
				});
			
			if(neighborList.isEmpty()){
				nodeList.remove(currentNode);
			} else{
				Collections.shuffle(neighborList);
				currentNode.connections.add(neighborList.get(0).getKey());
				neighborList.get(0).getValue().visited=true;
				nodeList.add(neighborList.get(0).getValue());
			}
		}
	}
	
	public MazeNode[][] getMaze(){
		return nodes;
	}
	
	
	public static MazeNode halfAndHalf(List<MazeNode> list){
		Random r = new Random();
		int rand = r.nextInt(2);
		if(rand == 0) return newestNodes(list);
		else return randomNodes(list);
	}
	
	public static MazeNode randomNodes(List<MazeNode> list){
		Random r = new Random();
		int rand = r.nextInt(list.size());
		return list.get(rand);
	}
	public static MazeNode newestNodes(List<MazeNode> list){
		return list.get(list.size()-1);
	}
	
	
	
	public static MazeNode oldestNodes(List<MazeNode> list){
		return list.get(0);
	}
	
	@FunctionalInterface 
	public interface NodeSelector{
		public MazeNode getNode(List<MazeNode> list);
	}
	
	
}
