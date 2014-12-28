package model;
import java.util.ArrayList;
import java.util.Collections;


public class DFS extends Searcher{

	ArrayList<State> visited;

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
		visited = new ArrayList<State>();
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
			if (!isContaining(visited, t))
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
