package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}
	
	public Department getDepartmentById(int id) {
		Department dept = departmentRepository.findById(id).orElse(null);
		System.out.println("Fetched department:" + dept);
		return dept;
	}
	
	public Department addOrUpdateDepartment(Department department) 	{
		return departmentRepository.save(department);
	}
	
	public Department deleteDepartment(Integer id) 	{
		Department deptToDelete = departmentRepository.findById(id).orElse(null);
		departmentRepository.delete(deptToDelete);
		return deptToDelete;
	}
	
	

}
