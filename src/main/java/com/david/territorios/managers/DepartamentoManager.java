package com.david.territorios.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.territorios.entidades.Departamento;

@Service
public interface DepartamentoManager {
	
	void save(Departamento departamento);
	
	Departamento getById(int id_departamento);
	
	void update(Departamento departamento);
	
	void deleteById(int id_departamento);
	
	List<Departamento> getAll();
}
