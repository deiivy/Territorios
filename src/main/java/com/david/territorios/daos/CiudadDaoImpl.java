package com.david.territorios.daos;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Ciudad;

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
public class CiudadDaoImpl implements CiudadDao{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Ciudad ciudad) {
		String query = "INSERT INTO CIUDAD (NOMBRE, ID_DEPARTAMENTO) values (?,?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {ciudad.getNombre().toUpperCase(), ciudad.getId_departamento()};
		jdbcTemplate.update(query, args);
	}
	
	@Override
	public Ciudad getById(int id_ciudad) {
		try{
			String query = "SELECT * FROM CIUDAD WHERE ID_CIUDAD = ?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			Ciudad ciu = jdbcTemplate.queryForObject(query, new Object[]{id_ciudad}, new RowMapper<Ciudad>(){
				@Override
				public Ciudad mapRow(ResultSet rs, int rowNum)throws SQLException {
					Ciudad ciudad = new Ciudad();
					ciudad.setId_ciudad(rs.getInt("ID_CIUDAD"));
					ciudad.setNombre(rs.getString("NOMBRE"));
					ciudad.setId_departamento(rs.getInt("ID_DEPARTAMENTO"));
					return ciudad;
				}});
			
			return ciu;
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void update(Ciudad ciudad) {
		String query = "UPDATE CIUDAD SET NOMBRE = ?, ID_DEPARTAMENTO = ? WHERE ID_CIUDAD = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {ciudad.getNombre().toUpperCase(), ciudad.getId_departamento()};
		jdbcTemplate.update(query, args);
	}

	@Override
	public void deleteById(int id_ciudad) {
		String query = "DELETE FROM CIUDAD WHERE ID_CIUDAD = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, id_ciudad);
	}

	@Override
	public List<Ciudad> getAll() {
		String query = "SELECT * FROM CIUDAD";
		List<Ciudad> ciudadList = new ArrayList<Ciudad>();
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> empRow : empRows){
			Ciudad ciudad = new Ciudad();
			ciudad.setId_ciudad(Integer.parseInt(String.valueOf(empRow.get("ID_CIUDAD"))));
			ciudad.setNombre(String.valueOf(empRow.get("NOMBRE")));
			ciudad.setId_departamento(Integer.parseInt(String.valueOf(empRow.get("ID_DEPARTAMENTO"))));
			ciudadList.add(ciudad);
		}
		return ciudadList;
	}

}
