package model.algorithm;
import java.util.ArrayList;

import model.algorithm.State;


public interface SearchDomain {
	
	public State getStart();
	public ArrayList<State> getAllPossibleStates(State s);
	public boolean isEnd(State s);
	public void print();
	
}
