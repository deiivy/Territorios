package com.david.territorios.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.territorios.entidades.Ciudad;

@Service
public interface CiudadManager {
	
	void save(Ciudad ciudad);
	
	Ciudad getById(int id_ciudad);
	
	void update(Ciudad ciudad);
	
	void deleteById(int id_ciudad);
	
	List<Ciudad> getAll();
}
