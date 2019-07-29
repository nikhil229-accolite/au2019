package com.au.assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Driver {
	
	private static void printList(List<Student> studentOutList) {
		// TODO Auto-generated method stub
		Integer i = 1;
		Iterator<Student> it = studentOutList.iterator();
		while(it.hasNext())
		{
			Student st = it.next();
			System.out.println("Student "+i+" details: ");
			System.out.println("First Name: "+ st.getFirstName()+"\n"+
								"Last Name: "+ st.getLastName()+"\n"+
								"Street: "+ st.getAddress().getStreet()+"\n"+
								"State: "+ st.getAddress().getState()+"\n"+
								"City: "+ st.getAddress().getCity()+"\n"+
								"Zip: " + st.getAddress().getZip()+"\n"+
								"Standard: " +st.getStandard()+"\n"+
								"Id: "+ st.getsId()+"\n"+
								"Division: "+st.getDivision()+"\n"+
								"MArks: "+st.getMarks());
			System.out.println("-------------------------------------------------------");
			i++;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub`
		//Address object for student 1
		
		List<Student> studentList = new ArrayList<Student>();
		List<Student> studentOutList = new ArrayList<Student>();
		SerialDeserialOps sdo = new SerialDeserialOps();
		
		Student s1 = new Student();
		s1.setsId(1);
		s1.setAddress(new Address("21/25 krishna nagar","Jaipur","Rajasthan",304040));
		s1.setStandard(6);
		s1.setFirstName("Allu");
		s1.setLastName("Arjun");
		s1.setDivision("B");

		s1.setMarks(376);
		
		Student s2 = new Student();
		s2.setsId(2);
		s2.setAddress(new Address("21 Kings Road","Castly Rock","Westros",304040));
		s2.setStandard(7);
		s2.setFirstName("Jamie");
		s2.setLastName("Lanister");
		s2.setDivision("A");

		s2.setMarks(434);
		
		studentList.add(s1);
		studentList.add(s2);

		
		sdo.writeToObject(studentList);
		
		
		try {
			studentOutList = (List<Student>)sdo.readToObject();
			printList(studentOutList);
			//System.out.println(studentOutList);
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.
		}
		

		
		
		
		

	}



}
