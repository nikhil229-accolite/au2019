package cm.accolite.au.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;

public class XmlOps {

	public static void ObjectToXml(Employee e1) {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
             
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
           //Store XML to File
            File file = new File("employee.xml");
             
            //Writes XML file to file-system
            jaxbMarshaller.marshal(e1, file);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

	}

	public static void xmlToObject()
	{
        File xmlFile = new File("employee.xml");
        
        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Employee employee = (Employee) jaxbUnmarshaller.unmarshal(xmlFile);
             
            System.out.println(employee);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
	}
}
	