package model;

import tasks.Task;

public interface Model extends Task {
	void selectDomain(String name); // select the domain
	void selectAlgorithm(String name); // select the algorithm
	void solveDomain(); // running the algorithm for solving the problem
	void exitDomain(); // exit the problem
	Solution getSolution(); // return the problem solution
}
