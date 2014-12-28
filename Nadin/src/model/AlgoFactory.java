package model;

public class AlgoFactory {
	
	public Searcher getAlgorithm(String name)
	{
		if (name.equals("BFS")) {
			return new BFS();
		}
		
		if (name.equals("DFS"))
		{
			return new DFS();
		}
		if (name.equals("Random"))
		{
			return new RandomSearcher();
		}
		return null;
	}
}
