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

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		return studentService.createOrSaveStudent(student);
	}
	
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.createOrSaveStudent(student);
	}
	
	@DeleteMapping("/students/{id}")
	public Student deleteStudent(@PathVariable int id) {
		return studentService.deleteStudent(id);
	}
	
}
