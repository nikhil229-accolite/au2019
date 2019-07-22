package org.Application;
import org.utility.Util;


public class App 
{
	public static void main(String[] args)
	{
		
		Book b1 = new Book(2,"The Concepts of Physics", "H.C Verma", 50);

		
		System.out.println(Util.convertToJSON(b1));
		
	}
    
}
