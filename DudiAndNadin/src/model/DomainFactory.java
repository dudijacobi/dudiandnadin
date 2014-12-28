package model;

import model.algorithm.SearchDomain;
import model.domain.MazeDomain;
import model.domain.SudokuDomain;

public class DomainFactory {
	
	public SearchDomain getDomain(String name)
	{
		if (name.equals("Maze"))
		{
			return new MazeDomain(6,7);
		}
		if (name.equals("Sudoku"))
		{
			return new SudokuDomain();
		}
		return null;
	}
}
