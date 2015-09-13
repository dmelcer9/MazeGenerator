package mazeGenerator;
import java.util.*;

public class MazeNode {
	
	public List<Direction> connections = new LinkedList<>();
	public boolean visited = false;
	
	public int x;
	public int y;
	
	public MazeNode(int x, int y){
		this.x=x;
		this.y=y;
	}
	
}
