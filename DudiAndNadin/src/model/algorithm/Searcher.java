package model.algorithm;
import java.util.ArrayList;


public abstract class Searcher {
	
	protected SearchDomain dom; // holds the domain for all children
	
	public abstract ArrayList<Action> search();

	public void setDomain(SearchDomain domain) {
		dom = domain;
	}

	/**
	 * check if the array contains the state
	 * @param visited the array
	 * @param t the state to search for
	 * @return
	 */
	protected boolean isContaining(ArrayList<State> visited, State t) {
		for (State i : visited)
		{
			if (t.equals(i))
			{
				return true;
			}
		}
		return false;
	}
	
}
