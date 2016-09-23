package com.david.territorios.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.territorios.daos.PaisDao;
import com.david.territorios.entidades.Pais;

@Service
public class PaisManagerImpl implements PaisManager {
	
	@Autowired
	PaisDao paisDao;
	
	@Override
	public void save(Pais pais) {
		try {
			paisDao.save(pais);
		} catch (Exception e) {
			
		}
	}

	@Override
	public Pais getById(int id_pais) {
		return paisDao.getById(id_pais);
	}

	@Override
	public void update(Pais pais) {
		paisDao.update(pais);
	}

	@Override
	public void deleteById(int id_pais) {
		paisDao.deleteById(id_pais);
	}

	@Override
	public List<Pais> getAll() {
		return paisDao.getAll();
	}

}
