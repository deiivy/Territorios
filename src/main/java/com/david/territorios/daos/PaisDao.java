package com.david.territorios.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Pais;


@Repository
public interface PaisDao {
	
	void save(Pais pais);
	
	Pais getById(int id_pais);
	
	void update(Pais pais);
	
	void deleteById(int id_pais);
		
	List<Pais> getAll();
}
