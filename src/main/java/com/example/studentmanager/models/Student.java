package com.example.studentmanager.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name="students")
public class Student{
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="studentnumber")
	private String studentNumber;
	@Column(name="fullname")
	private String fullName;
	@Column(name="department")
	private String department;
	

	public Student() {
	}

	
	public Student(String studentNumber, String fullName, String department) {
		this.studentNumber = studentNumber;
		this.fullName = fullName;
		this.department = department;
	}
	public Student(int id, String fullName, String department) {
		this.id = id;
		this.fullName = fullName;
		this.department = department;
	}

	public Student(int id, String studentNumber, String fullName, String department) {
		this.id = id;
		this.studentNumber = studentNumber;
		this.fullName = fullName;
		this.department = department;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentNumber() {
		return this.studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Student id(int id) {
		setId(id);
		return this;
	}

	public Student studentNumber(String studentNumber) {
		setStudentNumber(studentNumber);
		return this;
	}

	public Student fullName(String fullName) {
		setFullName(fullName);
		return this;
	}

	public Student department(String department) {
		setDepartment(department);
		return this;
	}	
	
}