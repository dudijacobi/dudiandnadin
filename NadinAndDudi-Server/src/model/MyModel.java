package model;

import java.util.ArrayList;
import java.util.Observable;

import model.algorithms.Action;
import model.algorithms.AlgoFactory;
import model.algorithms.Searcher;
import model.algorithms.Solution;
import model.algorithms.SolutionManager;
import model.domain.DomainFactory;
import model.domain.SearchDomain;

/**
 * Represents a specific model with algorithm and domain.
 *
 * @author Dudi and Nadin
 * 
 */
public class MyModel extends Observable implements Model
{

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
		String problemDescription = domain.getUniqDescription();
		solution = SolutionManager.getInstance().getSolution(problemDescription);
		if (solution == null)
		{
			algorithm.setDomain(domain);
			ArrayList<Action> result = algorithm.search();
			solution = new Solution(result);
			SolutionManager.getInstance().addSolution(problemDescription, solution);
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void setDomain(SearchDomain domain)
	{
		this.domain = domain;
	}

}
