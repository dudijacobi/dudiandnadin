package model;

import java.io.IOException;
import java.util.Observable;

import model.algorithms.AlgoFactory;
import model.algorithms.Searcher;
import model.algorithms.Solution;
import model.domain.DomainFactory;
import model.domain.SearchDomain;
import view.Client;

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
	public void selectDomain(String name)
	{
		DomainFactory domFactory = new DomainFactory();
		domain = domFactory.getDomain(name);
	}

	@Override
	public void selectAlgorithm(String name)
	{
		AlgoFactory algoFactory = new AlgoFactory();
		algorithm = algoFactory.getAlgorithm(name);
		domain.setAlgoName(name);
		setChanged();
		notifyObservers(domain);
	}

	@Override
	public void solveDomain()
	{
		if (algorithm == null || domain == null)
		{
			System.out.println("Error: You didn't select domain or algorithm");
			return;
		}
		String problemDescription = domain.getUniqDescription();
		Client client = new Client();
		try
		{
			solution = client.getSolution(domain);

		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
		}

		setChanged();
		notifyObservers();
	}

	@Override
	public Solution getSolution()
	{
		return solution;
	}

	@Override
	public void setDomain(SearchDomain domain)
	{
		this.domain = domain;
	}

}
