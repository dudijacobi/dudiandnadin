package model;
import java.util.ArrayList;
import java.util.Random;


public class RandomSearcher extends Searcher {

	public RandomSearcher(SearchDomain domain)
	{
		this.dom = domain;
	}
	
	public RandomSearcher() {
	}

	@Override
	public ArrayList<Action> search() {
		Random random = new Random();
		State state = dom.getStart();
		ArrayList<Action> actionList = new ArrayList<Action>();
		while (!dom.isEnd(state))
		{
			ArrayList<State> allPossib = dom.getAllPossibleStates(state);
			int index = random.nextInt(allPossib.size());
			// select one neighbor randomly  
			State next = allPossib.get(index);
			Action action = new Action();
			action.from = state;
			action.to = next;
			actionList.add(action);
			state = next;
		}
		return actionList;
	}

}
