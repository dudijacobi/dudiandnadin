package model.algorithms;

import java.util.ArrayList;

import model.domain.SearchDomain;
import model.domain.State;

/**
 * Represents the way of searching,
 * by witch Domain and by State's.
 * (abstract)
 * 
 * @author Dudi and Nadin
 *
 */
public abstract class Searcher {
	
	protected SearchDomain dom; // holds the domain for all children
	
	public abstract ArrayList<Action> search();

	public void setDomain(SearchDomain domain) {
		dom = domain;
	}

	/**
	 * Check if the array contains the state.
	 * @param visited the array.
	 * @param t the state to search for.
	 * @return true\false
	 * 
	 * @author Dudi and Nadin
	 * 
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
