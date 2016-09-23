package com.david.territorios.daos;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Departamento;
import com.david.territorios.entidades.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Repository
public class DepartamentoDaoImpl implements DepartamentoDao{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Departamento departamento) {
		String query = "INSERT INTO DEPARTAMENTO (NOMBRE, ID_PAIS) values (?,?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {departamento.getNombre().toUpperCase(), departamento.getId_pais()};
		jdbcTemplate.update(query, args);
	}
	
	@Override
	public Departamento getById(int id_departamento) {
		try{
			String query = "SELECT * FROM DEPARTAMENTO WHERE ID_DEPARTAMENTO = ?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			Departamento dep = jdbcTemplate.queryForObject(query, new Object[]{id_departamento}, new RowMapper<Departamento>(){
				@Override
				public Departamento mapRow(ResultSet rs, int rowNum)throws SQLException {
					Departamento departamento = new Departamento();
					departamento.setId_pais(rs.getInt("ID_DEPARTAMENTO"));
					departamento.setNombre(rs.getString("NOMBRE"));
					departamento.setId_pais(rs.getInt("ID_PAIS"));
					return departamento;
				}});
			
			return dep;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void update(Departamento departamento) {
		String query = "UPDATE DEPARTAMENTO SET NOMBRE = ?, ID_PAIS = ? WHERE ID_DEPARTAMENTO = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {departamento.getNombre().toUpperCase(), departamento.getId_pais()};
		jdbcTemplate.update(query, args);
	}

	@Override
	public void deleteById(int id_departamento) {
		String query = "DELETE D, C ";
		query += 	   "FROM DEPARTAMENTO D ";
		query += 	   "LEFT JOIN CIUDAD C ON C.ID_DEPARTAMENTO = D.ID_DEPARTAMENTO ";
		query += 	   "WHERE D.ID_DEPARTAMENTO = ? ";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, id_departamento);
	}

	@Override
	public List<Departamento> getAll() {
		String query =  "SELECT D.ID_DEPARTAMENTO, D.NOMBRE DEPARTAMENTO, D.ID_PAIS, P.NOMBRE PAIS, ";
			   query += "COUNT(C.ID_DEPARTAMENTO) CANTIDAD_CIUDADES ";
			   query += "FROM DEPARTAMENTO D ";
			   query += "INNER JOIN PAIS P ON P.ID_PAIS = D.ID_PAIS ";
			   query += "LEFT JOIN CIUDAD C ON C.ID_DEPARTAMENTO = D.ID_DEPARTAMENTO ";
			   query += "GROUP BY D.ID_DEPARTAMENTO ";
		
		List<Departamento> departamentoList = new ArrayList<Departamento>();
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> empRow : empRows){
			Departamento departamento = new Departamento();
			departamento.setId_departamento(Integer.parseInt(String.valueOf(empRow.get("ID_DEPARTAMENTO"))));
			departamento.setNombre(String.valueOf(empRow.get("DEPARTAMENTO")));
			departamento.setId_pais(Integer.parseInt(String.valueOf(empRow.get("ID_PAIS"))));
			departamento.setCantidad_ciudades(Integer.parseInt(String.valueOf(empRow.get("CANTIDAD_CIUDADES"))));
			Pais pais = new Pais();
			pais.setId_pais(departamento.getId_pais());
			pais.setNombre(String.valueOf(empRow.get("PAIS")));
			departamentoList.add(departamento);
			departamento.setPais(pais);
		}
		return departamentoList;
	}

}
