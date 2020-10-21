package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="teachers")
public class Teacher extends User {
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="department_id", nullable=false)
	@NotNull(message="A teacher must be assigned to a department")
	private Department department;
	
	@ManyToMany
	@JoinTable(name="teacher_modules",
		joinColumns = @JoinColumn(name = "teacher_id"), 
		inverseJoinColumns = @JoinColumn(name = "module_id"))
	private Set<ModuleIteration> taughtModules;

	@Override
	public String toString() {
		return "Teacher [department=" + department + "]";
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
