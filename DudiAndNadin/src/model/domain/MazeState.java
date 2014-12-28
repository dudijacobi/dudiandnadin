package model.domain;

import model.algorithm.State;

/**
 * represents a coordinate in the maze
 */
public class MazeState extends State{
	
	public int x;
	public int y;
	
	public boolean equals(Object obj) {
		MazeState other = (MazeState) obj;
		if (other.x == this.x && other.y == this.y)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
