package mazeGenerator.algorithms;
import mazeGenerator.*;

public enum Algorithms {
	
	RecursiveBacktracker("Recursive Backtracker"),
	GrowingTreeNewest("Growing Tree (Newest Node)"),
	GrowingTreeHalf("Growing Tree (50% Random, 50% Newest)"),
	GrowingTreeRandom("Growing Tree (Random Node)"),
	BinaryTree("Binary Tree");
	
	
	private String name;
	
	private Algorithms(String name){
		this.name=name;
	}
	
	public MazeGenerator getGenerator(int width, int height){
		
		switch(this){

		case BinaryTree:
			return new BinaryTree(width,height);

		case RecursiveBacktracker:
			return new RecursiveBacktracker(width,height);
			
		case GrowingTreeNewest:
			return new GrowingTree(width,height,GrowingTree::newestNodes);
			
		case GrowingTreeHalf:			
			return new GrowingTree(width,height,GrowingTree::halfAndHalf);
			
		case GrowingTreeRandom:			
			return new GrowingTree(width,height,GrowingTree::randomNodes);
			
		default:
			return null;


		}
	}
	
	public String toString(){
		return name;
	}
}
