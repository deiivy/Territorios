package com.david.territorios.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Departamento;



@Repository
public interface DepartamentoDao {
	
	void save(Departamento departamento);
	
	Departamento getById(int id_departamento);
	
	void update(Departamento departamento);
	
	void deleteById(int id_departamento);
	
	List<Departamento> getAll();
	
}
