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
	
	// Prometheus Counter that tracks total number of requests made for departments.
	private final Counter requestCount;
	
	public DepartmentController(CollectorRegistry collectorRegistry) {
		// Instantiate the Counter
		requestCount = Counter.build()
			.name("requests_departments_total") // Metric name
			.help("Total requests for departments.") // Metric description
			.register(collectorRegistry); // Bind it to default Spring Boot Collector Registry
	}
	
	@GetMapping("/departments/{deptId}")
	public Department getDepartmentById(@PathVariable int id) {
		// Increment the request counter each time a request is made
		requestCount.inc();
		return deptService.getDepartmentById(id);
	}
	
	@GetMapping("/departments")
	public List<Department> getDepartments() {
		// Increment the request counter each time a request is made
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
		requestCount.inc();
		department.setId(deptId);
		return deptService.addOrUpdateDepartment(department);
	}
	
	@DeleteMapping("/departments/{deptId}")
	public Department deleteDepartmentById(@PathVariable int id) {
		requestCount.inc();
		return deptService.deleteDepartment(id);
	}
	
}
