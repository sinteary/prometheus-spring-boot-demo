package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ModuleIteration;
import com.example.demo.repository.ModuleIterationRepository;

@Service
public class ModuleIterationService {
	@Autowired
	private ModuleIterationRepository moduleIterationRepository;
	
	public ModuleIteration createModuleIteration(ModuleIteration moduleIteration) {
		return moduleIterationRepository.save(moduleIteration);
	}
	
	public ModuleIteration getModuleIterationById(int id) {
		return moduleIterationRepository.findById(id).orElse(null);
	}
	
	public ModuleIteration updateModuleIteration(ModuleIteration moduleIteration, int id) {
		moduleIteration.setId(id);
		return moduleIterationRepository.save(moduleIteration);
	}
	
	public ModuleIteration deleteModuleIteration(int id) {
		ModuleIteration module = moduleIterationRepository.findById(id).orElse(null);
		moduleIterationRepository.deleteById(id);
		return module;
	}
}
