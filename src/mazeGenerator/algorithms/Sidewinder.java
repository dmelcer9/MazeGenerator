package mazeGenerator.algorithms;

import mazeGenerator.Direction;
import java.util.*;
import mazeGenerator.MazeGenerator;
import mazeGenerator.MazeNode;

public class Sidewinder extends MazeGenerator {

	public Sidewinder(int width, int height) {
		
		this.mazeWidth = width;
		this.mazeHeight= height;
		
		this.initializeNodes();
		
		for(int x = 0;x<mazeWidth-1;x++) nodes[x][0].connections.add(Direction.E);
		
		Random r = new Random();
		
		for(int y = 1;y<mazeHeight;y++){
			int runStart = 0;
			boolean inRun = false;
			for(int x = 0; x<mazeWidth;x++){
				
				if(!inRun){
					runStart = x;
					inRun = true;
				}
				
				if(r.nextBoolean() && x < mazeWidth-1){
					nodes[x][y].connections.add(Direction.E);
				} else{
					int difference = x - runStart;
					int upCarvePos = r.nextInt(difference+1)+runStart;
					nodes[upCarvePos][y].connections.add(Direction.N);
					inRun = false;
				}
			}
		}
	}

	@Override
	public MazeNode[][] getMaze() {
		// TODO Auto-generated method stub
		return nodes;
	}

}
