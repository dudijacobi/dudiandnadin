package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.algorithm.Action;

public class Solution implements Serializable {

	private static final long serialVersionUID = 3515117862993300331L;
	private ArrayList<Action> actions;
	private String problemDescription;
	

	public ArrayList<Action> getResult() {
		return actions;
	}

	public Solution(ArrayList<Action> actions) {
		this.actions = actions;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	
}
