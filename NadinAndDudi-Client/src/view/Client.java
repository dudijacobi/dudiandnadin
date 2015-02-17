package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBException;

import presenter.MyProperties;
import model.algorithms.Solution;
import model.domain.SearchDomain;

/**
 * Represent the making of the Client by socket.
 * 
 * @author Dudi and Nadin
 *
 */
public class Client
{

	public Solution getSolution(SearchDomain problem) throws UnknownHostException, IOException, ClassNotFoundException
	{
		Socket socket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;

		MyProperties props = new MyProperties();
		try
		{
			props.load();
		} catch (JAXBException e)
		{
		}
		try
		{
			socket = new Socket(props.ip, props.port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			out.writeObject(problem);
			return (Solution) in.readObject();
		}
		finally
		{
			try
			{
				out.close();
				in.close();
				socket.close();
			}
			catch(NullPointerException e)
			{}
		}
	}
}
