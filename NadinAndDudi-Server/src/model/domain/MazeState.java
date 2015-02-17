package model.domain;

/**
 * Represents a coordinate in the Maze.
 * 
 * @author Dudi and Nadin
 *
 */
public class MazeState extends State{
	
	public int x;
	public int y;
	
	public MazeState()
	{
	}
	
	public MazeState(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MazeState other = (MazeState) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
