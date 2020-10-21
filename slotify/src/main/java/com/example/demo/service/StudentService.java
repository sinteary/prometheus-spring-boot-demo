package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public List<Student> getStudents() {
		return repository.findAll();
	}
	
	public Student getStudentById(int id) {
		return repository.findById(id).orElse(null);		
	}
	
	public Student createOrSaveStudent(Student student) {
		return repository.save(student);
	}
	
	public Student deleteStudent(int id) {
		Student studentToDelete = repository.findById(id).orElse(null);
		repository.delete(studentToDelete);
		return studentToDelete;
	}

}
