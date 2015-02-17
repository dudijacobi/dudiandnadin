package model.algorithms;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the Solution of the Model and save them by results.
 * 
 * @author Dudi and Nadin
 *
 */
public class Solution implements Serializable{

	private ArrayList<Action> result;

	public ArrayList<Action> getResult() {
		return result;
	}

	public Solution(ArrayList<Action> result) {
		this.result = result;
	}

}
