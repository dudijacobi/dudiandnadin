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
 * Making the window widget of the problem selection menu.
 * 
 * @author Dudi and Nadin
 *
 */
public class ProblemSelectionDialog
{
	private static String result = "";
	public static String selectDomain()
	{
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Welcome!");
		shell.setSize(670, 400);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		shell.setLayout(rowLayout);
		new Label(shell, SWT.NONE).setText("Select game:");
		
		Composite types = new Composite(shell, SWT.BORDER);
		types.setLayout(new RowLayout(SWT.HORIZONTAL));
		Button sudoku = new Button(types, SWT.PUSH);
		sudoku.setImage(new Image(display, "Resources/sudoku.png"));
		sudoku.setSize(320, 320);
		sudoku.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				result = "Sudoku";
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		Button maze = new Button(types, SWT.PUSH);
		maze.setImage(new Image(display, "Resources/maze.jpg"));
		maze.setSize(320, 320);
		maze.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				result = "Maze";
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
