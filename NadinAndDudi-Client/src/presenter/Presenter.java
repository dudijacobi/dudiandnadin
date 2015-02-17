package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.MyModel;
import model.domain.SearchDomain;
import view.MyConsoleView;
import view.View;

/**
 * Initializes the View and the Model,
 * get a command and applying that command.
 * The Presenter also has a main method that generates a Model, View and Presenter.
 * 
 * @author Dudi and Nadin
 *
 */
public class Presenter implements Observer{

	private View view;
	private Model model;
	private String name;
	private HashMap<String, Runnable> commands;
	private Thread thread;

	public Presenter()
	{
		MyConsoleView myConsoleView = new MyConsoleView();
		myConsoleView.addObserver(this);
		view = myConsoleView;
		MyModel myModel = new MyModel();
		myModel.addObserver(this);
		model = myModel;
		initCommands();
	}
	
	private void initCommands() {
		commands = new HashMap<String, Runnable>();
		commands.put("solve", new Runnable() {
			
			@Override
			public void run() {
				model.solveDomain();
			}
		});
		commands.put("selectDomain", new Runnable() {
			
			@Override
			public void run() {
				model.selectDomain(name);
			}
		});
		commands.put("selectAlgo", new Runnable() {
			
			@Override
			public void run() {
				model.selectAlgorithm(name);
			}
		});
	}

	private void run() {
		view.start();
	}

	public static void main(String[] args) {
		System.out.println("Start!");
		new Presenter().run();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof View)
		{
			String cmd = (String) arg1;
			String[] split = cmd.split(" ");
			if (split.length > 1)
			{
				name = split[1];
			}
			Runnable runnable = commands.get(split[0]);
			if (runnable != null)
			{
				runnable.run();
			}
			else if ("finished?".equals(split[0]))
			{
				if (thread == null)
				{
					view.print("Not started");
				}
				else
				{
					view.print(""+ !thread.isAlive());
				}
			}
			else
			{
				System.out.println("Error: bad command");
			}
		}
		if (arg0 instanceof Model)
		{
			if (arg1 != null)
			{
				SearchDomain domain = (SearchDomain) arg1;
				view.showDomain(domain);
			}
			else
			{
				view.displaySolution(model.getSolution());
			}
		}
	}
	
}
