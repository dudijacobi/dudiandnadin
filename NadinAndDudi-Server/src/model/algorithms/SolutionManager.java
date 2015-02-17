package model.algorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * This function check if we already solve this problem and
 * if not we will save the solution in a new solution object.
 * 
 * @author Nadin and Dudi
 *
 */
public class SolutionManager {

	private HashMap<String, Solution> solutionsMap;
	private static SolutionManager instance = null;
	private static final String FILE_NAME = "solutions.dat";

	protected SolutionManager() {
		solutionsMap = new HashMap<String, Solution>();
		readSolutionsFromFile();
	}

	public static SolutionManager getInstance() {
		if (instance == null) {
			instance = new SolutionManager();
		}
		return instance;
	}

	public void addSolution(String key, Solution solution) {
		solutionsMap.put(key, solution);
		saveSolutionsInFile();
	}

	public Solution getSolution(String problemDescription) {
		return solutionsMap.get(problemDescription);
	}

	public void saveSolutionsInFile() {
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			out = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(out);
			oos.writeObject(solutionsMap);

		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
		{
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void readSolutionsFromFile() {
		FileInputStream in = null;
		ObjectInputStream ois = null;
		try {
			in = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(in);
			solutionsMap = (HashMap<String, Solution>) ois.readObject();

		} catch (FileNotFoundException e) {
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
