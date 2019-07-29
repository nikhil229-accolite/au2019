package com.au.assignment;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SerialDeserialOps  {
	
	//public String path = "C:\\users\\nikhil.taylor\\Desktop\\student.ser";
	

	
	public List<Student> readToObject() throws IOException
	{
		List<Student> studentList = null;

		try {
			FileInputStream fi = new FileInputStream("emp.xml");
			ObjectInputStream in = new ObjectInputStream(fi);
			
			//in.readObject();
			studentList = (List<Student>)in.readObject();
			
		
			in.close();
			fi.close();
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return studentList;
		
	}

	public void writeToObject(List<Student> studentList) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fo = new FileOutputStream("emp.xml");
			ObjectOutputStream out = new ObjectOutputStream(fo);
			
			out.writeObject(studentList);
			out.flush();
			out.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	

}

