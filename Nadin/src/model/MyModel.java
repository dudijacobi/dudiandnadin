package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Represents a specific model with algorithm and domain
 */
public class MyModel extends Observable implements Model {

	private SearchDomain domain = null;
	private Searcher algorithm = null;
	private Solution solution = null;
	
	@Override
	public void selectDomain(String name) {
		DomainFactory domFactory = new DomainFactory();
		domain = domFactory.getDomain(name);
	}

	@Override
	public void selectAlgorithm(String name) {
		AlgoFactory algoFactory = new AlgoFactory();
		algorithm = algoFactory.getAlgorithm(name);

	}

	@Override
	public void solveDomain() {
		if (algorithm == null || domain == null)
		{
			System.out.println("Error: You didn't select domain or algorithm");
			return;
		}
		algorithm.setDomain(domain);
		ArrayList<Action> result = algorithm.search();
		solution = new Solution(result);
		setChanged();
		notifyObservers();
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

}
