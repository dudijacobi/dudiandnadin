package model.algorithms;

import java.util.ArrayList;
import java.util.HashSet;

import model.domain.SearchDomain;
import model.domain.State;

/**
 *	DFS - (Depth First Search),
 *	it's an Algorithm Searcher.
 *
 *	DFS is an algorithm for data structures.
 *	Starts at the root (selecting some arbitrary node as the root in the case of a graph)
 *	and explores as far as possible along each branch before backtracking.
 *
 * @author Dudi and Nadin
 *
 */
public class DFS extends Searcher{

	HashSet<State> visited;

	public DFS(SearchDomain domain)
	{
		this.dom = domain;
	}
	public DFS()
	{
	}
	
	@Override
	public ArrayList<Action> search() 
	{
		State first = dom.getStart();
		visited = new HashSet<State>();
		return searchNode(first, new ArrayList<Action>());
	}
	
	private ArrayList<Action> searchNode(State state, ArrayList<Action> actionList) {
		
		visited.add(state);
		if (dom.isEnd(state))
		{
			return actionList;
		}
		for (State t : dom.getAllPossibleStates(state))
		{
			if (!visited.contains(t))
			{
				Action action = new Action();
				action.from = state;
				action.to = t;
				actionList.add(action);
				ArrayList<Action> result = searchNode(t, actionList);
				if (result != null)
				{
					return result;
				}
				actionList.remove(actionList.size() - 1);
			}
		}
		
		return null;
	}
	
}
