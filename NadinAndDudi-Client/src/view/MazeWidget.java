package view;

import javax.xml.bind.JAXBException;

import model.algorithms.Action;
import model.algorithms.Solution;
import model.domain.MazeDomain;
import model.domain.MazeState;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

/**
 * Making the widget of the Maze.
 * 
 * @author Dudi and Nadin
 *
 */
public class MazeWidget extends Canvas
{

	private static int xsize;
	private static int ysize;
	private SelectionListener sl;
	private MazeDomain domain;
	private Label isLegal;

	public MazeWidget(final Composite shell, boolean showSolve,
			MazeDomain domain, SelectionListener solveListener, Solution solution)
	{
		super(shell, SWT.NONE);
		Composite parent = new Composite(shell, SWT.BORDER);
		parent.setBounds(10, 10, shell.getSize().x - 38, shell.getSize().x - 20);
		this.domain = domain;
		MazeState start = (MazeState) domain.getStart();
		xsize = domain.getXSize();
		ysize = domain.getYSize();
		this.sl = solveListener;
		GridLayout layout = new GridLayout(ysize, false);
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		parent.setLayout(layout);

		GridData gridData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		final int BOX_SIZE = parent.getSize().x;
		gridData.heightHint = BOX_SIZE;
		gridData.widthHint = BOX_SIZE;
		gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;

		String [][] solutionArray= new String[ysize][];
		prepareSolution(solution, solutionArray);
		
		// Code to display board.
		for (int i = 0; i < ysize; i++)
		{
			for (int j = 0; j < xsize; j++)
			{
				Composite cell = new Composite(parent, SWT.BORDER);
				cell.setBounds(j * BOX_SIZE / xsize, i * BOX_SIZE
						/ ysize, BOX_SIZE / xsize, BOX_SIZE
						/ ysize);
				cell.setLayoutData(gridData);
				cell.setLayout(new GridLayout());

				Button b = new Button(cell, SWT.PUSH);
				if (start.x == j && start.y == i)
				{
					b.setImage(new Image(getDisplay(), "Resources/fig.png"));
				}
				else if (domain.isEnd(new MazeState(j, i)))
				{
					b.setImage(new Image(getDisplay(), "Resources/target.png"));
				}
				b.setSize(BOX_SIZE / xsize, BOX_SIZE / ysize);
				b.setEnabled(false);
				if (solution != null)
				{
					if (solutionArray[i][j].length() > 0)
					{
						b.setImage(new Image(getDisplay(), solutionArray[i][j]));
					}
				}
			}
		}

		if (showSolve)
		{
			Button button = new Button(shell, SWT.PUSH);
			button.addSelectionListener(sl);
			button.setBounds(BOX_SIZE / 2 - 60, BOX_SIZE + 70, 140, 30);
			button.setText("Solve with " + domain.getAlgorithmName());
			
		}

		isLegal = new Label(shell, SWT.NONE);
		isLegal.setBounds(BOX_SIZE / 2 - 40, BOX_SIZE + 40, 140, 30);
		isLegal.setText("Illegal numbers!");
		isLegal.setForeground(new org.eclipse.swt.graphics.Color(
				getDisplay(), 255, 0, 0));
		isLegal.setVisible(false);
		
		shell.getShell().setMenuBar(new Menu (shell.getShell(), SWT.BAR));
		MenuItem mainItem = new MenuItem (shell.getShell().getMenuBar(), SWT.CASCADE);
		mainItem.setText ("&File");
		Menu submenu = new Menu (shell.getShell(), SWT.DROP_DOWN);
		mainItem.setMenu (submenu);
		
		MenuItem propertiesItem = new MenuItem (submenu, SWT.PUSH);
		MenuItem exitItem = new MenuItem (submenu, SWT.PUSH);
		propertiesItem.setText ("Setting");
		exitItem.setText ("Exit");

		exitItem.addListener (SWT.Selection, new Listener () {
			@Override
			public void handleEvent (Event e) {
				MessageBox messageBox = new MessageBox(shell.getShell(), SWT.ICON_QUESTION
						| SWT.YES | SWT.NO);
				messageBox.setMessage("Do you really want to exit?");
				messageBox.setText("Exiting Application");
				int response = messageBox.open();
				if (response == SWT.YES)
					System.exit(0);
			}
		});
		propertiesItem.addListener (SWT.Selection, new Listener () {
			@Override
			public void handleEvent (Event e) {
				try
				{
					new Properties(shell.getDisplay());
				} catch (JAXBException e1)
				{
				}
			}
		});
	}

	/**
	 * @param solution
	 * @param solutionArray
	 */
	public void prepareSolution(Solution solution, String[][] solutionArray)
	{
		if (solution != null)
		{
			for (int i = 0; i < ysize; i++)
			{
				solutionArray[i] = new String[xsize];
				for (int j = 0; j < xsize; j++)
				{
					solutionArray[i][j] = "";
				}
			}
			for (int i = 0; i < solution.getResult().size(); i++)
			{
				Action action = solution.getResult().get(i);
				MazeState from = (MazeState) action.from;
				MazeState to = (MazeState) action.to;
				if (from.x == to.x-1)
					solutionArray[from.y][from.x] = "Resources/arrow_right.gif";
				else if (from.x == to.x+1)
					solutionArray[from.y][from.x] = "Resources/arrow_left.gif";
				else if (from.y == to.y-1)
					solutionArray[from.y][from.x] = "Resources/arrow_down.png";
				else if (from.y == to.y+1)
					solutionArray[from.y][from.x] = "Resources/arrow_up.jpg";
				System.out.println(to);
				
			}
		}
	}

}
