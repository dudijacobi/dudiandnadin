package model.domain;
import java.util.ArrayList;

import model.domain.SudokuState;
import model.algorithm.SearchDomain;
import model.algorithm.State;

public class SudokuDomain implements SearchDomain {

	@Override
	public State getStart() {
		SudokuState state = new SudokuState();
		state.getSudoku()[0][1] = 2;
		state.getSudoku()[0][2] = 3;
		state.getSudoku()[1][0] = 1;
		state.getSudoku()[1][3] = 4;
		state.getSudoku()[2][0] = 3;
		state.getSudoku()[2][3] = 2;
		state.getSudoku()[3][1] = 4;
		state.getSudoku()[3][2] = 1;
		
		
//		state.getSudoku()[0][0] = 2;
//		state.getSudoku()[0][1] = 3;
//		state.getSudoku()[0][3] = 6;
//		state.getSudoku()[0][5] = 4;
//		state.getSudoku()[1][3] = 1;
//		state.getSudoku()[1][5] = 2;
//		state.getSudoku()[2][0] = 1;
//		state.getSudoku()[2][1] = 6;
//		state.getSudoku()[3][4] = 4;
//		state.getSudoku()[3][5] = 6;
//		state.getSudoku()[4][0] = 4;
//		state.getSudoku()[4][2] = 6;
//		state.getSudoku()[5][0] = 6;
//		state.getSudoku()[5][2] = 4;
//		state.getSudoku()[5][4] = 5;
//		state.getSudoku()[5][5] = 1;
		return state;

	}

	@Override
	public boolean isEnd(State state) {
		SudokuState s = (SudokuState) state;
		for (int i = 0; i < s.getSudoku().length; i++) {
			for (int j = 0; j < s.getSudoku().length; j++) {
				if (s.getSudoku()[i][j] == 0)
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
				if (state.getSudoku()[i][j] != 0)
					continue;
				for (int k = 0; k < state.getSudoku().length; k++) {
					SudokuState newState = new SudokuState();
					newState.setSudoku(copyState(state.getSudoku()));
					newState.getSudoku()[i][j] = k + 1;
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

	private boolean isLegal(SudokuState s)
	{
		for (int i = 0; i < s.getSudoku().length; i++) {
			boolean temp[] = new boolean[s.getSudoku().length];
			for (int j = 0; j < s.getSudoku().length; j++) {
				int value = s.getSudoku()[i][j] - 1;
				if (value != -1)
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
				if (value != -1)
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

}
