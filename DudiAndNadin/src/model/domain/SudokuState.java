package model.domain;

import model.algorithm.State;
//import java.util.Arrays;

public class SudokuState extends State {

	private static final int SIZE = 4;
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

	@Override
	public boolean equals(Object obj) {
		SudokuState other = (SudokuState) obj;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (other.sudoku[i][j] != this.sudoku[i][j])
					return false;
			}
		}
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
