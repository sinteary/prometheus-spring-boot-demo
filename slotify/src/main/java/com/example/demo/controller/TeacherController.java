package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.TeacherService;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/teachers")
	public List<Teacher> getTeachers() {
		return teacherService.getTeachers();
	}
	
	@GetMapping("/departments/{deptId}/teachers")
	@ResponseBody
	public List<Teacher> getTeachersByDepartment(@PathVariable int deptId) {
		Department dept = departmentService.getDepartmentById(deptId);
		return teacherService.getTeachersByDepartment(dept);
	}
	
	@GetMapping("/teachers/{id}")
	public Teacher getTeachersById(@PathVariable int id) {
		return teacherService.getTeacherById(id);
	}
	
	public void updateTeacherDepartment(Teacher teacher, int deptId) {
		Department newDept = departmentService.getDepartmentById(deptId);
		System.out.println("Department: " + newDept);
		teacher.setDepartment(newDept);
	}
	
	@PutMapping("/departments/{deptId}/teachers/{teacherId}")
	public Teacher updateTeacher(@PathVariable (value="deptId") int deptId, 
			@PathVariable(value="teacherId") int teacherId,
			@RequestBody Teacher teacher) {
		teacher.setId(teacherId);
		updateTeacherDepartment(teacher, deptId);
		return teacherService.addOrUpdateTeacher(teacher);
	}
	
	@PostMapping("/departments/{deptId}/teachers")
	public Teacher addTeacher(@PathVariable int deptId,
			@RequestBody Teacher teacher) {
		updateTeacherDepartment(teacher, deptId);
		return teacherService.addOrUpdateTeacher(teacher);
	}
	
	@DeleteMapping("/teachers/{id}")
	public Teacher deleteTeacher(@PathVariable int id) {
		return teacherService.deleteTeacher(id);
	}

}
