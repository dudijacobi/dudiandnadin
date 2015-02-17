package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Represent the windows of the algorithm selection.
 * 
 * @author Dudi and Nadin
 *
 */
public class AlgoSelectionDialog
{
	private static String result = "";
	public static String selectAlgo()
	{
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Algorithm");
		shell.setSize(570, 250);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		shell.setLayout(rowLayout);
		new Label(shell, SWT.NONE).setText("Select algorithm:");
		
		Composite types = new Composite(shell, SWT.BORDER);
		types.setLayout(new RowLayout(SWT.HORIZONTAL));
		Button sudoku = new Button(types, SWT.PUSH);
		sudoku.setImage(new Image(display, "Resources/BFS.png"));
		sudoku.setSize(170, 170);
		sudoku.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				result = "BFS";
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
			}
		});
		
		Button maze = new Button(types, SWT.PUSH);
		maze.setImage(new Image(display, "Resources/DFS.gif"));
		maze.setSize(170, 170);
		maze.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				result = "DFS";
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
			}
		});

		Button random = new Button(types, SWT.PUSH);
		random.setImage(new Image(display, "Resources/random.jpg"));
		random.setSize(170, 170);
		random.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				result = "Random";
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
			}
		});
		
		
		shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
	    display.dispose();
		return result;
	}
}
