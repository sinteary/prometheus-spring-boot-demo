package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="modules")
public class ModuleCore {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="Module must have a code")
	@NotBlank(message="Module code cannot be blank")
	@JsonProperty("name")
	private String name;
	
	@NotNull(message="Module must have a name")
	@NotBlank(message="Module name cannot be blank")
	@JsonProperty("code")
	private String code;
	
	@OneToMany(mappedBy="module")
	private List<ModuleIteration> moduleIterations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModuleCode() {
		return code;
	}

	public void setModuleCode(String code) {
		this.code = code;
	}

	public String getModuleName() {
		return name;
	}

	public void setModuleName(String name) {
		this.name = name;
	}
	
}
