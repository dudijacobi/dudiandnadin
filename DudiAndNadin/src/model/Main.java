package model;
import java.util.ArrayList;
import java.util.Date;

import model.algorithm.Action;
import model.algorithm.BFS;
import model.algorithm.DFS;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;
import model.domain.MazeDomain;
import model.domain.MazeState;
import model.domain.SudokuDomain;
import model.domain.SudokuState;



public class Main {
	
	public static void main(String[] args) {
		SearchDomain m = new MazeDomain(6,6);
		m.print();
		
		Searcher searcher = new DFS(m);
		
		ArrayList<Action> result = searcher.search();
		
		for (Action a : result)
		{
			MazeState from = (MazeState) a.from;
			MazeState to = (MazeState) a.to;
			System.out.println(from.x + "," + from.y + "->" + to.x + "," + to.y);
		}

		searcher = new RandomSearcher(m);
		
		m.print();
		result = searcher.search();
		
		for (Action a : result)
		{
			MazeState from = (MazeState) a.from;
			MazeState to = (MazeState) a.to;
			System.out.println(from.x + "," + from.y + "->" + to.x + "," + to.y);
		}

		searcher = new BFS(m);
		
		m.print();
		result = searcher.search();
		
		for (Action a : result)
		{
			MazeState from = (MazeState) a.from;
			MazeState to = (MazeState) a.to;
			System.out.println(from.x + "," + from.y + "->" + to.x + "," + to.y);
		}
		
		/*
		 * Sudoku
		 */
		
		m = new SudokuDomain();
		((SudokuState) m.getStart()).print();
		System.out.println("-------------------------");
		
		Date date = new Date();
		System.out.println(date.toString());
		
		searcher = new BFS(m);
		m.print();
		result = searcher.search();
		
		if (result != null)
		{
			for (Action a : result)
			{
				SudokuState to = (SudokuState) a.to;
				to.print();
				System.out.println("-------------------------");
			}
		}
		else
		{
			System.out.println("Solution not found");
		}
		date = new Date();
		System.out.println(date.toString());
		
		
		searcher = new DFS(m);
		m.print();
		result = searcher.search();
		
		if (result != null)
		{
			for (Action a : result)
			{
				SudokuState to = (SudokuState) a.to;
				to.print();
				System.out.println("-------------------------");
			}
		}
		else
		{
			System.out.println("Solution not found");
		}
		date = new Date();
		System.out.println(date.toString());
	}
}
