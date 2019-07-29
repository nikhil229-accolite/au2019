package com.au.assignment;

import java.io.Serializable;

public class Student extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sId;
	private Integer standard;
	private String division;
	private Integer marks;
	private Address address;
	

	
	

	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	public Integer getStandard() {
		return standard;
	}
	public void setStandard(Integer standard) {
		this.standard = standard;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public Integer getMarks() {
		return marks;
	}
	public void setMarks(Integer marks) {
		this.marks = marks;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public void  setFirstName(String firstName)
	{
		super.firstName = firstName;
	}
	
	
	@Override
	public void  setLastName(String lastName)
	{
		super.lastName = lastName;
	}
	

}
