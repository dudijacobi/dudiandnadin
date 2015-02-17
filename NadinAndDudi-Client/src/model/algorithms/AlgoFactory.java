package model.algorithms;

/**
 *	Represents the Algorithm Factory that get an Algorithm and create one.
 *	for example: BFS, DFS, Random.
 *
 * @author Dudi and Nadin
 *
 */
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
