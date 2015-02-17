package presenter;

import java.io.File;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Reads the Properties by 'server ip' and 'port' from XML file.
 * 
 * @author Dudi and Nadin
 *
 */
@XmlRootElement
public class MyProperties implements Serializable
{
	@XmlElement
	public String server_ip;
	@XmlElement
	public int port;

	public void load() throws JAXBException
	{
		Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(
				MyProperties.class).createUnmarshaller();
		File file = new File("properties.xml");
		MyProperties myProp = (MyProperties) jaxbUnmarshaller.unmarshal(file);
		server_ip = myProp.server_ip;
		port = myProp.port;
	}

}
