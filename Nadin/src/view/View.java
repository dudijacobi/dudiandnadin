package view;

import model.Solution;

public interface View {
	void start();
	String getUserAction();
	
	void displayCurrentState();
	void displaySolution(Solution s);
	
}
