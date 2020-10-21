package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Department;
import com.example.demo.model.Teacher;

public interface TeacherRepository extends UserBaseRepository<Teacher> {

	List<Teacher> findByDepartment(Department department);

}