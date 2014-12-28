package model;
import java.util.ArrayList;


public interface SearchDomain {
	
	public State getStart();
	public ArrayList<State> getAllPossibleStates(State s);
	public boolean isEnd(State s);
	public void print();
	
}
