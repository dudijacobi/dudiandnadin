package model;

import java.util.ArrayList;
import java.util.Observable;

import model.algorithm.Action;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;

/**
 * Represents a specific model with algorithm and domain
 */
public class MyModel extends Observable implements Model {

	private SearchDomain domain = null;
	private Searcher algorithm = null;
	private Solution solution = null;
	private SolutionManager soultionManager;				// Read from.
	
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
		soultionManager.addSolution(solution);
		setChanged();
		notifyObservers();
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		solveDomain();
	}

	@Override
	public void exitDomain() {
		// TODO Auto-generated method stub
		
	}

}
