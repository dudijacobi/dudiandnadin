package presenter;

import java.io.File;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Saves the Properties by 'ip' and 'port' in XML file.
 * 
 * @author Dudi and Nadin
 *
 */
@XmlRootElement
public class MyProperties implements Serializable
{
	@XmlElement
	public String ip;
	@XmlElement
	public int port;

	public void load() throws JAXBException
	{
		Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(
				MyProperties.class).createUnmarshaller();
		File file = new File("properties.xml");
		MyProperties myProp = (MyProperties) jaxbUnmarshaller.unmarshal(file);
		ip = myProp.ip;
		port = myProp.port;
	}

}
