package model.domain;

import java.util.Arrays;

/**
 * Represents a coordinate in the Sudoku.
 * 
 * @author Dudi and Nadin
 * 
 */
public class SudokuState extends State {

	private static final int SIZE = 6;
	private int[][] sudoku;

	public SudokuState() {
		sudoku = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			sudoku[i] = new int[SIZE];
			for (int j = 0; j < SIZE; j++) {
				this.sudoku[i][j] = 0;
			}
		}
	}
	
	public void setSudoku(int[][] sudoku) {
		this.sudoku = sudoku;
	}
	
	public int[][] getSudoku() {
		return sudoku;
	}

	int result = 1;
	
	@Override
	public int hashCode()
	{
		if (result != 1)
			return result;
		final int prime = 31;
		result = prime * result + Arrays.hashCode(sudoku);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SudokuState other = (SudokuState) obj;
		if (!Arrays.deepEquals(sudoku, other.sudoku))
			return false;
		return true;
	}
	
	public void print()
	{
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + this.sudoku[i][j]);
			}
			System.out.println();
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				str += " " + this.sudoku[i][j];
			}
			str += "\n";
		}
		return str;
	}
	
	
	
}
