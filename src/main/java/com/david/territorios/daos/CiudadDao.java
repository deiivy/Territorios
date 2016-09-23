package com.david.territorios.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Ciudad;



@Repository
public interface CiudadDao {
	
	void save(Ciudad ciudad);
	
	Ciudad getById(int id_Ciudad);
	
	void update(Ciudad Ciudad);
	
	void deleteById(int id_Ciudad);
	
	List<Ciudad> getAll();
	
}
