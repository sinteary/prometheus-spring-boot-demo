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

import com.example.demo.model.ModuleCore;
import com.example.demo.service.ModuleService;

import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.CollectorRegistry;

@RestController
public class ModuleController {
	@Autowired
	private ModuleService moduleService;

	/**
		Prometheus Histogram that tracks the frequency of response durations that
		fall into pre-defined buckets
	*/
	private final Histogram requestDuration;
	
	/**
		Prometheus Summary that tracks the 95th percentile of response times for GET /modules.
	*/
	private final Summary requestDurationSummary;
	
	public ModuleController(CollectorRegistry collectorRegistry) {
		requestDuration = Histogram.build()
			// By default: 10 buckets measuring network response durations (less than 10s)
			// Users can initialize their own bucket values as shown below
			.buckets(0.005, 0.01, 0.015, 0.02, 0.025, 0.03, 0.035, 0.04)
			.name("request_modules_duration")
			.help("Time taken for requests for modules.")
			.register(collectorRegistry);
		
		requestDurationSummary = Summary.build()
			.name("request_modules_duration_summary")
			.help("Time taken for requests for modules (summary).")
			// Track 95th percentile, with 1% tolerated error
			.quantile(0.95, 0.01)
			.register(collectorRegistry);
	}
	
	@GetMapping("/modules")
	public List<ModuleCore> getAllModuleCores() {
		Histogram.Timer timer_histogram = requestDuration.startTimer(); // Begin timing
		Summary.Timer timer_summary = requestDurationSummary.startTimer(); // Begin timing
		List<ModuleCore> modules = moduleService.getAllModules();
		timer_histogram.observeDuration(); // Record time taken
		timer_summary.observeDuration(); // Record time taken
		return modules;	
	}
	
	@GetMapping("/modules/{id}")
	public ModuleCore getModuleCoreById(@PathVariable int id) {
		return moduleService.getModuleById(id);
	}
	
	@GetMapping("/modules/{code}")
	public ModuleCore getAllModuleCores(@PathVariable String code) {
		return moduleService.getModuleByCode(code);
	}
	
	@PostMapping("/modules")
	public ModuleCore createModuleCore(@RequestBody ModuleCore module) {
		return moduleService.createModule(module);
	}
	
	@PutMapping("/modules/{modId}")
	public ModuleCore updateModuleCore(@RequestBody ModuleCore module, @PathVariable int modId) {
		return moduleService.updateModule(module, modId);
	}
	
	@DeleteMapping("/modules/{modId}")
	public ModuleCore deleteModuleCore(@PathVariable int modId) {
		ModuleCore module = moduleService.getModuleById(modId);
		moduleService.deleteModule(modId);
		return module;
	}
}
