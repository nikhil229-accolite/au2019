package com.service;

public class Employee {

	private Integer sID;
	private String firstName;
	private String lastName;
	private Integer age;
	
	
	public Employee(Integer sID, String firstName, String lastName, Integer age) {
		super();
		this.sID = sID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Integer getsID() {
		return sID;
	}
	public void setsID(Integer sID) {
		this.sID = sID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
