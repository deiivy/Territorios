package com.david.territorios.managers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.territorios.entidades.Pais;

@Service
public interface PaisManager {
	
	void save(Pais pais);
	
	Pais getById(int id_pais);
	
	void update(Pais pais);
	
	void deleteById(int id_pais);
	
	List<Pais> getAll();
}
