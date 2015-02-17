package view;

import model.algorithms.Solution;

public interface View {
	void start();
	String getUserAction();
	
	void displayCurrentState();
	void displaySolution(Solution s);
	void print(String string);
	
}
