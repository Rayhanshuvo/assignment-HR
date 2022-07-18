package com.leads.assignment;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Employee {

	private int id;
	private String empId;
	private String firstName;
	private String lastName;
	private String title; 
	private String division;
	private String building;
	private String room;



	public Employee(int id, String empId, String firstName, String lastName, String title, String division,
			String building, String room) {
		super();
		this.id = id;
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.division = division;
		this.building = building;
		this.room = room;
	}




	public Employee(String empId, String firstName, String lastName, String title, String division, String building,
			String room) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.division = division;
		this.building = building;
		this.room = room;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Employee() {

	}
	public Employee(int id) {
	
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", division=" + division
				+ ", building=" + building + ", room=" + room + "]";
	}
	

}
