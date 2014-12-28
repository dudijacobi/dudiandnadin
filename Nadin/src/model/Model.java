package model;

public interface Model {
	void selectDomain(String name); // select the domain
	void selectAlgorithm(String name); // select the algorithm
	void solveDomain(); // running the algorithm for solving the problem
	Solution getSolution(); // return the problem solution
}
