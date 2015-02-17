package view;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import presenter.MyProperties;

public class Properties
{
	public Properties(Display display) throws JAXBException
	{
		final MyProperties myProperties = new MyProperties();
		myProperties.load();
		final Shell shell = new Shell(display);
		shell.setSize(200, 200);

		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.marginTop = 20;
		rowLayout.marginLeft = 20;
		shell.setLayout(rowLayout);

		Label labelIP = new Label(shell, SWT.NONE);
		labelIP.setText("IP:");
		final Text textIP = new Text(shell, SWT.BORDER);
		textIP.setText(myProperties.ip);

		Label labelPort = new Label(shell, SWT.NONE);
		labelPort.setText("Port:");
		final Text textPort = new Text(shell, SWT.BORDER);
		textPort.setText("" + myProperties.port);
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Save && Close");
		button.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				JAXBContext jaxbContext;
				try
				{
					myProperties.ip = textIP.getText();
					myProperties.port = Integer.valueOf(textPort.getText());
					jaxbContext = JAXBContext.newInstance(MyProperties.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					jaxbMarshaller.marshal(myProperties, new File("properties.xml"));
				} catch (JAXBException e)
				{

				}
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
			}
		});


		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
