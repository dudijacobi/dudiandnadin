package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.JAXBException;

import model.Model;
import model.MyModel;
import server.MyTCPIPServer;
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

	public static void main(String[] args)
	{
		MyProperties props = new MyProperties();
		try
		{
			props.load();
			MyTCPIPServer server = new MyTCPIPServer(props.port);
			server.startServer(5);
			server.getThread().join();
			server.stopServer();
		} catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
				thread = new Thread(runnable);
				thread.start();
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
			view.displaySolution(model.getSolution());
		}
	}
	
}
