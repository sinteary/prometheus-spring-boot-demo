package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ModuleCore;
import com.example.demo.repository.ModuleRepository;

@Service
public class ModuleService {
	@Autowired
	private ModuleRepository moduleRepository;
	
	public ModuleCore createModule(ModuleCore module) {
		return moduleRepository.save(module);
	}
	
	public List<ModuleCore> getAllModules() {
		return moduleRepository.findAll();
	}
	
	public ModuleCore getModuleById(int id) {
		return moduleRepository.findById(id).orElse(null);
	}
	
	public ModuleCore getModuleByCode(String code) {
		return moduleRepository.findByCode(code).orElse(null);
	}
	
	public ModuleCore updateModule(ModuleCore module, int id) {
		module.setId(id);
		return moduleRepository.save(module);
	}
	
	public ModuleCore deleteModule(int id) {
		ModuleCore module = moduleRepository.findById(id).orElse(null);
		moduleRepository.deleteById(id);
		return module;
	}
	
}