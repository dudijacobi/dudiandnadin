package view;

import javax.xml.bind.JAXBException;

import model.domain.SearchDomain;
import model.domain.SudokuDomain;
import model.domain.SudokuState;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.widgets.Text;

/**
 * Create the Sudoku Widget.
 * 
 * @author Dudi and Nadin
 *
 */
public class SudokuWidget extends Canvas
{

	private final class ModifyListenerImplementation implements ModifyListener
	{
		private int i;
		private int j;
		private Text text;

		public ModifyListenerImplementation(int i, int j, Text label)
		{
			this.i = i;
			this.j = j;
			this.text = label;
		}

		@Override
		public void modifyText(ModifyEvent arg0)
		{
			String text = this.text.getText();
			int value = 0;
			try
			{
				value = Integer.valueOf(text);
			} catch (NumberFormatException e)
			{
				value = 0;
			}
			((SudokuState) sudokuDomain.getStart()).getSudoku()[i][j] = value;
			if (!SudokuDomain.isLegal(((SudokuState) sudokuDomain.getStart())))
			{
				isLegal.setVisible(true);
			}
			else
			{
				isLegal.setVisible(false);
			}
		}
	}

	private static int sudoku_size;
	private SelectionListener sl;
	private SudokuDomain sudokuDomain;
	private Label isLegal;

	public SudokuWidget(final Composite shell, boolean showSolve,
			SearchDomain domain, SelectionListener solveListener)
	{
		super(shell, SWT.NONE);
		Composite parent = new Composite(shell, SWT.BORDER);
		parent.setBounds(10, 10, shell.getSize().x - 38, shell.getSize().x - 20);
		this.sudokuDomain = (SudokuDomain) domain;
		SudokuState start = (SudokuState) sudokuDomain.getStart();
		sudoku_size = start.getSudoku().length;
		this.sl = solveListener;
		GridLayout layout = new GridLayout(sudoku_size, false);
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

		// code to display board
		for (int i = 0; i < sudoku_size; i++)
		{
			for (int j = 0; j < sudoku_size; j++)
			{
				Composite labelCell = new Composite(parent, SWT.BORDER);
				labelCell.setBounds(j * BOX_SIZE / sudoku_size, i * BOX_SIZE
						/ sudoku_size, BOX_SIZE / sudoku_size, BOX_SIZE
						/ sudoku_size);
				labelCell.setLayoutData(gridData);
				labelCell.setLayout(new GridLayout());

				Text label = new Text(labelCell, SWT.SINGLE);
				if (start.getSudoku()[i][j] != 0)
				{
					label.setText(Integer.toString(start.getSudoku()[i][j]));
				}
				else
				{

				}
				label.setSize(BOX_SIZE / sudoku_size, BOX_SIZE / sudoku_size);
				label.addModifyListener(new ModifyListenerImplementation(i, j,
						label));
				label.setEditable(showSolve);
			}
		}

		if (showSolve)
		{
			Button button = new Button(shell, SWT.PUSH);
			button.addSelectionListener(sl);
			button.setBounds(BOX_SIZE / 2 - 60, BOX_SIZE + 70, 140, 30);
			button.setText("Solve with " + domain.getAlgorithmName());

			isLegal = new Label(shell, SWT.NONE);
			isLegal.setBounds(BOX_SIZE / 2 - 40, BOX_SIZE + 40, 140, 30);
			isLegal.setText("Illegal numbers!");
			isLegal.setForeground(new org.eclipse.swt.graphics.Color(
					getDisplay(), 255, 0, 0));
			isLegal.setVisible(false);
		}
		else
		{
			Label button = new Label(shell, SWT.NONE);
			button.setBounds(BOX_SIZE / 2 - 50, BOX_SIZE + 70, 140, 30);
			button.setText("Sudoku solved!");
		}
		
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

}
