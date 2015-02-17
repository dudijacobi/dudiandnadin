package model.domain;

import java.util.ArrayList;


public interface SearchDomain {
	
	public State getStart();
	public ArrayList<State> getAllPossibleStates(State s);
	public boolean isEnd(State s);
	public void print();
	public String getUniqDescription();
	public String getDomainName();
	public String getAlgorithmName();
	public void setAlgoName(String name);
	
}
