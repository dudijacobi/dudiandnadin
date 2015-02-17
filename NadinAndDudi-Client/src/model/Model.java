package model;

import model.algorithms.Solution;
import model.domain.SearchDomain;

/**
 * Represents the model through selection of the Domain and Algorithm,
 * and also to solve the Domain and Get a Solution.
 * (interface)
 * 
 * @author Dudi and Nadin
 * 
 */
public interface Model {
	void selectDomain(String name);					// Select the domain.
	void selectAlgorithm(String name);				// Select the algorithm.
	void solveDomain();								// Running the algorithm for solving the problem.
	Solution getSolution();							// Return the problem solution.
	void setDomain(SearchDomain domain);
}
