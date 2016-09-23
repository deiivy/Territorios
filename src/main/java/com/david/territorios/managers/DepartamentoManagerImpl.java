package com.david.territorios.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.territorios.daos.DepartamentoDao;
import com.david.territorios.entidades.Departamento;

@Service
public class DepartamentoManagerImpl implements DepartamentoManager {
	
	@Autowired
	DepartamentoDao departamentoDao;
	
	@Override
	public void save(Departamento departamento) {
		try {
			departamento.setId_pais(departamento.getPais().getId_pais());
			departamentoDao.save(departamento);
		} catch (Exception e) {
			
		}
	}

	@Override
	public Departamento getById(int id_departamento) {
		return departamentoDao.getById(id_departamento);
	}

	@Override
	public void update(Departamento departamento) {
		departamentoDao.update(departamento);
	}

	@Override
	public void deleteById(int id_departamento) {
		departamentoDao.deleteById(id_departamento);
	}

	@Override
	public List<Departamento> getAll() {
		return departamentoDao.getAll();
	}

}
