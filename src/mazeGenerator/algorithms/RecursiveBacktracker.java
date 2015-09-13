package mazeGenerator.algorithms;

import mazeGenerator.*;

import java.util.*;



public class RecursiveBacktracker extends MazeGenerator {

	public RecursiveBacktracker(int width, int height){
		
		this.mazeWidth = width;
		this.mazeHeight = height;
		
		this.initializeNodes();
		this.generate();
		
	}
	public class StackEntry{
		public HashEntry<Direction,MazeNode> receivingNode;
		public MazeNode callingNode;
		public StackEntry(MazeNode caller,HashEntry<Direction,MazeNode> receiver){
			this.callingNode= caller;
			this.receivingNode= receiver;
		}
	}
	public MazeNode[][] getMaze(){
		return nodes;
	}
	private MazeNode[][] generate(){
		
		Stack<StackEntry> stack = new Stack<>();
		
		stack.push(new StackEntry(null,new HashEntry<>(null,nodes[0][0])));
		
		while(!stack.empty()){
			StackEntry entry = stack.pop();
			MazeNode node = entry.receivingNode.getValue();
			if(!node.visited){
				
				if(entry.callingNode!= null){
					entry.callingNode.connections.add(entry.receivingNode.getKey());
				}
				
				node.visited=true;
				List<HashEntry<Direction,MazeNode>> neighbors= new LinkedList<>();
				this.getNeighbors(node).forEach((d,m)->(neighbors.add(new HashEntry<>(d,m))));
			
				Collections.shuffle(neighbors);
				for(HashEntry<Direction,MazeNode> currentNode: neighbors){
					stack.push(new StackEntry(node,currentNode));
				}
			}
		}
		
		return nodes;
	}
	
}
