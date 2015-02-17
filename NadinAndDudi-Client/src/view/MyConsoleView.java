package view;

import java.util.Observable;

import model.algorithms.Solution;
import model.domain.MazeDomain;
import model.domain.SearchDomain;
import model.domain.SudokuDomain;
import model.domain.SudokuState;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Making the Main Menu view.
 * 
 * @author Dudi and Nadin
 *
 */
public class MyConsoleView extends Observable implements View {

	private Shell waitingShell;
	private Solution solution;

	@Override
	public void start() {
		/*
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
		*/
		String selectDomain = ProblemSelectionDialog.selectDomain();
		setChanged();
		notifyObservers("selectDomain " + selectDomain);

		String selectAlgo = AlgoSelectionDialog.selectAlgo();
		setChanged();
		notifyObservers("selectAlgo " + selectAlgo);
	}

	/* 
	 * Action is passed as a parameter to observers.
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
		this.solution = s;
		/*
		for(Action a : s.getResult())
		{
			System.out.println("ACTION:");
			System.out.println(a.from);
			System.out.println("--to--");
			System.out.println(a.to);
		}
		*/
	}

	@Override
	public void print(String string)
	{
		System.out.println(string);
	}

	@Override
	public void showDomain(SearchDomain domain)
	{
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText(domain.getDomainName());
		shell.setSize(570, 690);
		
		getDomainWidget(domain, shell);
		
		shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
	    if (solution == null)
	    {
	    	waitingShell = new Shell(display);
	    	waitingShell.setText("Solving");
	    	Label label = new Label(waitingShell, SWT.NONE);
	    	label.setBounds(10, 10, 100, 30);
	    	label.setText("Please wait...");
	    	waitingShell.setSize(220, 70);
	    	waitingShell.open();
	    	while (!waitingShell.isDisposed()) {
	    		if (!display.readAndDispatch())
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e)
					{
					}
	    		if (solution!=null)
	    			waitingShell.close();
	    	}
	    }
		showSolution(domain, display, shell);
	}

	/**
	 * @param domain
	 * @param shell
	 */
	public void getDomainWidget(SearchDomain domain, final Shell shell)
	{
		if (domain instanceof SudokuDomain)
		{
			new SudokuWidget(shell, true, domain, new SelectionListener()
			{
				
				@Override
				public void widgetSelected(SelectionEvent arg0)
				{
					shell.close();
					new Thread(new Runnable()
					{
						
						@Override
						public void run()
						{
							setChanged();
							notifyObservers("solve");
						}
					}).start();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0){}
			});
		}
		else
		{
			new MazeWidget(shell, true, (MazeDomain) domain, new SelectionListener()
			{
				
				@Override
				public void widgetSelected(SelectionEvent arg0)
				{
					shell.close();
					new Thread(new Runnable()
					{
						
						@Override
						public void run()
						{
							setChanged();
							notifyObservers("solve");
						}
					}).start();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0){}
			}, null);
		}
	}

	/**
	 * @param domain
	 * @param display
	 * @param shell
	 */
	public void showSolution(SearchDomain domain, Display display,
			final Shell shell)
	{
		if (solution == null || solution.getResult() == null)
		{
			int style = SWT.ICON_ERROR;
		    
		    MessageBox messageBox = new MessageBox(new Shell(display), style);
		    messageBox.setMessage("Sorry, no solution..");
		    messageBox.open();
		    display.dispose();
		    return;
		}
		if (domain instanceof SudokuDomain)
		{
			SudokuState finalState = (SudokuState) solution.getResult().get(solution.getResult().size() - 1).to;
			((SudokuState)domain.getStart()).setSudoku(finalState.getSudoku());
			
			final Shell shell3 = new Shell(display);
			shell3.setText(domain.getDomainName());
			shell3.setSize(570, 690);
			
			new SudokuWidget(shell3, false, domain,null);
			
			shell3.open();
			while (!shell3.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		}
		else
		{
			final Shell shell3 = new Shell(display);
			shell3.setText(domain.getDomainName());
			shell3.setSize(570, 690);
			
			new MazeWidget(shell3, false, (MazeDomain) domain,null, solution);
			
			shell3.open();
			while (!shell3.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		}
		
		display.dispose();
	}
	
}
