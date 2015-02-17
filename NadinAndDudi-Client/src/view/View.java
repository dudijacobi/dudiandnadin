package view;

import model.algorithms.Solution;
import model.domain.SearchDomain;

public interface View {
	void start();
	String getUserAction();
	
	void displayCurrentState();
	void displaySolution(Solution s);
	void print(String string);
	void showDomain(SearchDomain domain);
	
}
