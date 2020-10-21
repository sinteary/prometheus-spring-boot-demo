package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="students")
public class Student extends User {
	private int year;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="student_modules",
		joinColumns = @JoinColumn(name = "student_id"), 
	  inverseJoinColumns = @JoinColumn(name = "module_id"))
	private List<ModuleIteration> enrolledModules;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
