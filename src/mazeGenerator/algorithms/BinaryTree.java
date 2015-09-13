package mazeGenerator.algorithms;

import mazeGenerator.Direction;
import mazeGenerator.MazeGenerator;
import mazeGenerator.MazeNode;
import java.util.*;

public class BinaryTree extends MazeGenerator {
	
	public BinaryTree(int width, int height){
		
		this.mazeWidth = width;
		this.mazeHeight = height;
		
		this.initializeNodes();
		Random r = new Random();
		for(int x = 0;x<mazeWidth;x++){
			for(int y=0;y<mazeHeight;y++){
				
				if(y!=0 && x < mazeWidth -1){
					
					int randint = r.nextInt(2);
					Direction d = (randint == 0)?Direction.E:Direction.N;
					nodes[x][y].connections.add(d);
							
				} else if(y!=0 && x == mazeWidth - 1){
					
					nodes[x][y].connections.add(Direction.N);
					
				} else if(y == 0 && x< mazeWidth - 1){
					
					nodes[x][y].connections.add(Direction.E);
					
				}
			}
		}
		
	}
	@Override
	public MazeNode[][] getMaze() {
		return nodes;
	}

}
