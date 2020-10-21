package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="module_iterations", uniqueConstraints= {
		@UniqueConstraint(columnNames = {"module_id", "academic_year", "semester"})		
})
public class ModuleIteration {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@ManyToOne
	@JoinColumn(name="module_id",nullable=false)
	private ModuleCore module;
	
	@NotNull
	@Column(name="academic_year")
	private String academicYear;
	
	@NotNull
	@Column(name="semester")
	private int semester;
	
	@ManyToOne
	@JoinColumn
	private Department department;
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToMany(mappedBy="enrolledModules")
	private List<Student> enrolledStudents;
	
	@ManyToMany(mappedBy="taughtModules")
	private List<Teacher> teachingStaff;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public ModuleCore getModule() {
		return module;
	}

	public void setModule(ModuleCore module) {
		this.module = module;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	
}
