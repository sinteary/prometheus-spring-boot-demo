package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	
	public List<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	public List<Teacher> getTeachersByDepartment(Department department) {
		return teacherRepository.findByDepartment(department);
	}
	
	public Teacher getTeacherById(int id) {
		return teacherRepository.findById(id).orElse(null);
	}
	
	public Teacher addOrUpdateTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public Teacher deleteTeacher(int id) {
		Teacher teacherToDelete = teacherRepository.findById(id).orElse(null);
		teacherRepository.deleteById(id);
		return teacherToDelete;
	}
	
}
