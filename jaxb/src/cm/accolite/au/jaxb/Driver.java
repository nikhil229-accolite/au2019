package cm.accolite.au.jaxb;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Employee e1 = new Employee(1,"jamie","lanister",new Address("21","castly rock","westros",304040));
		//final String xmlPath = "C:\\users\\nikhil.taylor\\Desktop\\emp.xml";
		XmlOps.ObjectToXml(e1);
		
		
		XmlOps.xmlToObject();
		}
}
