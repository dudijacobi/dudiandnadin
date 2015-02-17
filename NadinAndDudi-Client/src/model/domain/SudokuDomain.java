package model.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the Sudoku Domain by State's and get all the possible moves.
 * 
 * @author Dudi and Nadin
 *
 */
public class SudokuDomain implements SearchDomain, Serializable {

	private String algoName;
	private SudokuState startState;

	public SudokuDomain()
	{
		startState = new SudokuState();
		
		
		startState.getSudoku()[0][0] = 2;
		startState.getSudoku()[0][1] = 3;
		startState.getSudoku()[0][3] = 6;
		startState.getSudoku()[0][5] = 4;
		startState.getSudoku()[1][1] = 4;
		startState.getSudoku()[1][2] = 3;
		startState.getSudoku()[1][3] = 1;
		startState.getSudoku()[1][5] = 2;
		startState.getSudoku()[2][0] = 1;
		startState.getSudoku()[2][1] = 6;
		startState.getSudoku()[3][2] = 1;
		startState.getSudoku()[3][4] = 4;
		startState.getSudoku()[3][5] = 6;
		startState.getSudoku()[4][0] = 4;
		startState.getSudoku()[4][1] = 1;
		startState.getSudoku()[4][2] = 6;
		startState.getSudoku()[5][0] = 6;
		startState.getSudoku()[5][1] = 2;
		startState.getSudoku()[5][2] = 4;
		startState.getSudoku()[5][4] = 5;
		startState.getSudoku()[5][5] = 1;
		
//		startState.getSudoku()[0][1] = 2;
//		startState.getSudoku()[0][2] = 3;
//		startState.getSudoku()[1][0] = 1;
//		startState.getSudoku()[1][3] = 4;
//		startState.getSudoku()[2][0] = 3;
//		startState.getSudoku()[2][3] = 2;
//		startState.getSudoku()[3][1] = 4;
//		startState.getSudoku()[3][2] = 1;
	}
	
	@Override
	public State getStart() {
		return startState;

	}

	@Override
	public boolean isEnd(State state) {
		SudokuState s = (SudokuState) state;
		for (int i = 0; i < s.getSudoku().length; i++) {
			for (int j = 0; j < s.getSudoku().length; j++) {
				if (s.getSudoku()[i][j] <= 0)
					return false;
			}
		}
		return true;
	}

	@Override
	public void print() 
	{
		
	}

	@Override
	public ArrayList<State> getAllPossibleStates(State s) {
		ArrayList<State> result = new ArrayList<State>();
		SudokuState state = (SudokuState) s;
		for (int i = 0; i < state.getSudoku().length; i++) {
			for (int j = 0; j < state.getSudoku().length; j++) {
				if (state.getSudoku()[i][j] > 0)
					continue;
				for (int k = 1; k <= state.getSudoku().length; k++) {
					SudokuState newState = new SudokuState();
					newState.setSudoku(copyState(state.getSudoku()));
					newState.getSudoku()[i][j] = k;
					if (isLegal(newState))
					{
						result.add(newState);
					}
				}
				
			}
		}
		return result;
	}
	
	private int[][] copyState(int[][] sudoku) {
		int [][] arr = new int[sudoku.length][sudoku.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new int[sudoku.length];
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = sudoku[i][j];
			}
		}
		return arr;
	}

	public static boolean isLegal(SudokuState s)
	{
		for (int i = 0; i < s.getSudoku().length; i++) {
			boolean temp[] = new boolean[s.getSudoku().length];
			for (int j = 0; j < s.getSudoku().length; j++) {
				int value = s.getSudoku()[i][j] - 1;
				if (value < -2 || value >= temp.length)
					return false;
				if (value > -1)
				{
					if (temp[value] == true)
					{
						return false;
					}
					temp[value] = true;
				}
			}
		}
		for (int i = 0; i < s.getSudoku().length; i++) {
			boolean temp[] = new boolean[s.getSudoku().length];
			for (int j = 0; j < s.getSudoku().length; j++) {
				int value = s.getSudoku()[j][i] - 1;
				if (value < -2 || value >= temp.length)
					return false;
				if (value > -1)
				{
					if (temp[value] == true)
					{
						return false;
					}
					temp[value] = true;
				}
			}
		}
		return true;
	}

	@Override
	public String getUniqDescription()
	{
		String s = "";
		int[][] sudoku = ((SudokuState)getStart()).getSudoku();
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				s += sudoku[i][j];
			}
		}
		return s;
	}
	

	@Override
	public String getDomainName()
	{
		return "Sudoku";
	}

	@Override
	public String getAlgorithmName()
	{
		return algoName;
	}
	
	public void setAlgoName(String algoName)
	{
		this.algoName = algoName;
	}

}
