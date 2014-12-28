package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SolutionManager {
	
	private HashMap<String, Solution> solutionsMap;
	private static SolutionManager instance = null;
	private static final String FILE_NAME = "solutions.dat";
	
	protected SolutionManager() {	
		solutionsMap = new HashMap<String, Solution>();
		read();
	}
	
	public static SolutionManager getInstance() {
		if (instance == null) {
			instance = new SolutionManager();			
		}
		return instance;
	}
	
	public void addSolution(Solution solution) {
		solutionsMap.put(solution.getProblemDescription(), solution);
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
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readSolutionsFromFile() 
	{
		//File f = new File(FILE_NAME);
		FileReader inputFile = null;										//Create object of FileReader.
        BufferedReader bufferReader = null;
		 try{
	          inputFile = new FileReader(FILE_NAME);						//Create object of FileReader.
	          bufferReader = new BufferedReader(inputFile);					//Instantiate the BufferedReader Class.
	          
	          String line;													//Variable to hold the one line data.
	          while ((line = bufferReader.readLine()) != null)   {			// Read file line by line and print on the console.
	            System.out.println(line);
	          }
	          bufferReader.close();											//Close the buffer reader.
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
	}
	
}
