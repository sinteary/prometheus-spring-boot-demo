package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ModuleCore;


public interface ModuleRepository extends JpaRepository<ModuleCore, Integer> {

	Optional<ModuleCore> findByCode(String moduleCode);

}
