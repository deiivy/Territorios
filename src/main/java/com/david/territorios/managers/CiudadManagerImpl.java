package com.david.territorios.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.territorios.daos.CiudadDao;
import com.david.territorios.entidades.Ciudad;

@Service
public class CiudadManagerImpl implements CiudadManager {
	
	@Autowired
	CiudadDao ciudadDao;
	
	@Override
	public void save(Ciudad ciudad) {
		try {
			ciudad.setId_departamento(ciudad.getDepartamento().getId_departamento());
			ciudadDao.save(ciudad);
		} catch (Exception e) {
			
		}
	}

	@Override
	public Ciudad getById(int id_ciudad) {
		return ciudadDao.getById(id_ciudad);
	}

	@Override
	public void update(Ciudad ciudad) {
		ciudadDao.update(ciudad);
	}

	@Override
	public void deleteById(int id_ciudad) {
		ciudadDao.deleteById(id_ciudad);
	}

	@Override
	public List<Ciudad> getAll() {
		return ciudadDao.getAll();
	}

}
