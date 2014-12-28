package view;

import java.util.Observable;
import java.util.Scanner;

import model.Solution;
import model.algorithm.Action;

public class MyConsoleView extends Observable implements View {

	@Override
	public void start() {
		Scanner s = new Scanner(System.in);
		while (true)
		{
			System.out.println("Enter command:");
			String cmd = s.nextLine();
			if("exit".equals(cmd))
			{
				break;
			}
			else
			{
				setChanged();
				notifyObservers(cmd);
			}
		}
		s.close();
	}

	/* 
	 * action is passed as a parameter to observers
	 */
	@Override
	public String getUserAction() {
		return null;
	}

	@Override
	public void displayCurrentState() {

	}

	@Override
	public void displaySolution(Solution s) {
		for(Action a : s.getResult())
		{
			System.out.println("ACTION:");
			System.out.println(a.from);
			System.out.println("--to--");
			System.out.println(a.to);
		}
	}

}
