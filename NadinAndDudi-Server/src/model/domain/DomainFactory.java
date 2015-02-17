package model.domain;

/**
 *	Represents the Domain Factory that get an Domain and create one.
 *	for example: MazeDomain(6,7) or SudokuDomain().
 *
 * @author Dudi and Nadin
 *
 */
public class DomainFactory {
	
	public SearchDomain getDomain(String name)
	{
		if (name.equals("Maze"))
		{
			return new MazeDomain(6,8);
		}
		if (name.equals("Sudoku"))
		{
			return new SudokuDomain();
		}
		return null;
	}
}
