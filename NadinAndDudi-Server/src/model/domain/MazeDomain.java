package model.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the Maze Domain by X,Y State's and get all the possible moves.
 * 
 * @author Dudi and Nadin
 *
 */
public class MazeDomain implements SearchDomain, Serializable {

	
	private int x_size; 
	private int y_size;
	private String algoName;

	public MazeDomain(int x, int y) {						// C'tor.
		this.x_size = x; 
		this.y_size = y;
	}

	public int getXSize()
	{
		return x_size;
	}
	
	public int getYSize()
	{
		return y_size;
	}
	
	@Override
	public State getStart() {								// This function return the maze start at 0,0.
		MazeState s = new MazeState(); 
		s.x = 0; 
		s.y = 0; 
		return s; 
	}

	@Override
	/**
	 * This function return a list of the all possible states.
	 * 
	 * @author Dudi and Nadin
	 * 
	 */
	public ArrayList<State> getAllPossibleStates(State state) {
		ArrayList<State> arr = new ArrayList<State>(); 
		MazeState s = (MazeState) state; 
		MazeState left = new MazeState(); 
		left.x = s.x - 1;									// Left definition.
		left.y = s.y;										// Left definition.
		if (isStateOK(left)) 
			arr.add(left);									// Add to the list if it's ok.
		MazeState right = new MazeState(); 
		right.x = s.x + 1;									// Right definition.
		right.y = s.y; 										// Right definition.
		if (isStateOK(right)) 								// As long as right state is ok.
			arr.add(right); 								// Add to the list if it's ok.
		MazeState up = new MazeState(); 
		up.x = s.x; 										// Up definition. 
		up.y = s.y + 1; 									// Up definition. 
		if (isStateOK(up)) 
			arr.add(up); 									// Add to the list if it's ok.
		MazeState down = new MazeState(); 
		down.x = s.x; 										// Down definition.
		down.y = s.y - 1; 									// Down definition.
		if (isStateOK(down)) 
			arr.add(down); 									// Add to the list if it's ok.
		return arr; 
	}

	/**
	 * Check if position is legal.
	 * 
	 * @author Dudi and Nadin
	 * 
	 */
	private boolean isStateOK(MazeState s) { 
		return s.x >= 0 && s.y >= 0 && s.x < x_size && s.y < y_size;
	}

	@Override
	public boolean isEnd(State state) { 					// This function checking if it's the end.
		MazeState s = (MazeState)state; 
		if (s.x == 2 && s.y == 5) 
		{
			return true; 
		}
		return false;
	}

	@Override
	public void print() { 									// This function print the maze.
		for (int i = 0; i < y_size; i++) { 					// Go over all the mazestate rows.
			for (int j = 0; j < x_size; j++) {				// Go over all the mazestate columns.
				MazeState s = new MazeState(); 
				s.x = i;
				s.y = j;
				if (isEnd(s))								// If it is the end.
				{
					System.out.print("X ");  
				}
				else
				{
					System.out.print("O ");
				}
			}
			System.out.println(); 
		}
	}

	@Override
	public String getUniqDescription()
	{
		// Solve every time.
		return "" + x_size + "," + y_size + getStart() + System.currentTimeMillis();
	}

	@Override
	public String getDomainName()
	{
		return "Maze";
	}

	@Override
	public String getAlgorithmName()
	{
		return algoName;
	}
	
	public void setAlgoName(String algoName)
	{
		this.algoName = algoName;
	}

}
