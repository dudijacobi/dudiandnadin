package model.algorithms;

import java.util.ArrayList;
import java.util.Collections;

import model.domain.SearchDomain;
import model.domain.State;

/**
 *	BFS - (Breadth First Search),
 *	it's an Algorithm Searcher.
 *
 *	BFS search is limited to essentially two operations:
 *	1. Visit and inspect a node of a graph.
 *	2. Gain access to visit the nodes that neighbor the currently visited node.
 *
 * @author Dudi and Nadin
 *
 */
public class BFS extends Searcher{

	public BFS(SearchDomain domain)
	{
		this.dom = domain;
	}
	public BFS()
	{
	}
	
	@Override
	public ArrayList<Action> search() 
	{
		State first = dom.getStart();
		QueueNode qn = new QueueNode();
		qn.state = first;
		ArrayList<State> visited = new ArrayList<State>();
		
		ArrayList<QueueNode> queue = new ArrayList<QueueNode>();
		queue.add(qn);
		while(!queue.isEmpty())
		{
			QueueNode n = queue.remove(0);
			visited.add(n.state);
			if (dom.isEnd(n.state))
			{
				return makeActionList(n);
			}
			for (State t : dom.getAllPossibleStates(n.state))
			{
				if (!isContaining(visited, t))
				{
					QueueNode tn = new QueueNode();
					tn.state = t;
					tn.prev = n;
					queue.add(tn);
				}
			}
		}
		return null;
	}

	private ArrayList<Action> makeActionList(QueueNode n) {
		
		ArrayList<Action> list = new ArrayList<Action>();
		while (n.prev != null)
		{
			Action action = new Action();
			action.to = n.state;
			action.from = n.prev.state;
			list.add(action);
			n = n.prev;
		}
		Collections.reverse(list);
		return list;
	}

	class QueueNode
	{
		public State state;
		public QueueNode prev;
	}
	
}
