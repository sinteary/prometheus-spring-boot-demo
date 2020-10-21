package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ModuleCore;
import com.example.demo.model.ModuleIteration;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.ModuleIterationService;
import com.example.demo.service.ModuleService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;

@RestController
public class ModuleIterationController {
	
	@Autowired
	private ModuleIterationService moduleIterationService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping("/departments/{deptId}/modules/{moduleId}")
	public ModuleIteration createModuleIteration(
		@PathVariable (value="moduleId") int moduleId,
		@PathVariable (value="deptId") int deptId,
		@RequestBody ModuleIteration moduleIteration) {
		ModuleCore module = moduleService.getModuleById(moduleId);
		moduleIteration.setModule(module);
		return moduleIterationService.createModuleIteration(moduleIteration);
	}
	
//	@PutMapping("/departments/{deptId}/modules/{moduleId}/{iterationId}")
//	public ModuleIteration updateModuleIteration(@RequestBody ModuleIteration moduleIteration,
//			@PathVariable (value="moduleId") int moduleId,
//			@PathVariable (value="deptId") int deptId) {
//		
//	}
	
	@PutMapping("/moduleiterations/{moduleIterationId}/student/{studentId}")
	public void enrollStudent(@PathVariable (value="moduleIterationId") int moduleId,
			@PathVariable (value="studentId") int studentId ) {
		
		
	}
	
}
