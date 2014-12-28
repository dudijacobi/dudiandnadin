package model;
import java.util.ArrayList;


public class MazeDomain implements SearchDomain {

	
	private int x_size; 
	private int y_size;

	public MazeDomain(int x, int y) { //ctor
		this.x_size = x; 
		this.y_size = y;
	}

	@Override
	public State getStart() { //this function return the maze start at 0,0
		MazeState s = new MazeState(); 
		s.x = 0; 
		s.y = 0; 
		return s; 
	}

	@Override
	/**
	 * this function return a list of the all possible states
	 */
	public ArrayList<State> getAllPossibleStates(State state) {
		ArrayList<State> arr = new ArrayList<State>(); 
		MazeState s = (MazeState) state; 
		MazeState left = new MazeState(); 
		left.x = s.x - 1; //left definition 
		left.y = s.y; //left definition 
		if (isStateOK(left)) 
			arr.add(left); //add to the list if it's ok
		MazeState right = new MazeState(); 
		right.x = s.x + 1; //right definition 
		right.y = s.y; //right definition 
		if (isStateOK(right)) //as long as right state is ok
			arr.add(right); //add to the list if it's ok
		MazeState up = new MazeState(); 
		up.x = s.x; //up definition 
		up.y = s.y + 1; //up definition 
		if (isStateOK(up)) 
			arr.add(up); //add to the list if it's ok
		MazeState down = new MazeState(); 
		down.x = s.x; //down definition 
		down.y = s.y - 1; //down definition 
		if (isStateOK(down)) 
			arr.add(down); //add to the list if it's ok
		return arr; 
	}

	/**
	 * check if posiotion is legal
	 */
	private boolean isStateOK(MazeState s) { 
		return s.x >= 0 && s.y >= 0 && s.x < x_size && s.y < y_size;
	}

	@Override
	public boolean isEnd(State state) { //this function checking if it's the end
		MazeState s = (MazeState)state; 
		if (s.x == 3 && s.y == 3) 
		{
			return true; 
		}
		return false;
	}

	@Override
	public void print() { //this function print the maze
		for (int i = 0; i < y_size; i++) { //go over all the mazestate rows
			for (int j = 0; j < x_size; j++) { //go over all the mazestate columns
				MazeState s = new MazeState(); 
				s.x = i;
				s.y = j;
				if (isEnd(s)) //if it is the end
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

}
