package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;

import io.prometheus.client.Counter;
import io.prometheus.client.CollectorRegistry;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentService deptService;
	private final Counter requestCount;
	
	public DepartmentController(CollectorRegistry collectorRegistry) {
		requestCount = Counter.build()
			.name("requests_departments_total")
			.help("Total requests for departments.")
			.register(collectorRegistry);
	}
	
	@GetMapping("/departments/{deptId}")
	public Department getDepartmentById(@PathVariable int id) {
		requestCount.inc();
		return deptService.getDepartmentById(id);
	}
	
	@GetMapping("/departments")
	public List<Department> getDepartments() {
		requestCount.inc();
		return deptService.getDepartments();
	}
	
	@PostMapping("/departments")
	public Department addDepartment(@RequestBody Department dept) {
		requestCount.inc();
		return deptService.addOrUpdateDepartment(dept);
	}
	
	@PutMapping("/departments/{deptId}")
	public Department updateDepartment(@PathVariable int deptId, @RequestBody Department department) {
		department.setId(deptId);
		return deptService.addOrUpdateDepartment(department);
	}
	
	@DeleteMapping("/departments/{deptId}")
	public Department deleteDepartmentById(@PathVariable int id) {
		return deptService.deleteDepartment(id);
	}
	
}
