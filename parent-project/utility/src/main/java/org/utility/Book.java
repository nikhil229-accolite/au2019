package org.utility;



public class Book implements Comparable<Book>{
	
	private int id;
	private String name;
	private String authorName;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Book(int id, String name, String authorName, int price) {
		super();
		this.id = id;
		this.name = name;
		this.authorName = authorName;
		this.price = price;
	}
	


@Override
public String toString()
{
	return this.id +" "+ this.name +" " + this.authorName +authorName + " "  + this.price;
	
}
public int compareTo(Book o) {
	// TODO Auto-generated method stub
	if(o==null)
	{
		return 1;
	}
	if(this.price > o.price)
	{
		return 1;
		
	}
	if(this.price < o.price)
	{
		return -1;
	}
	return 0;
	
}


}

