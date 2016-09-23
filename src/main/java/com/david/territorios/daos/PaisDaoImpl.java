package com.david.territorios.daos;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Repository
public class PaisDaoImpl implements PaisDao{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Pais pais) {
		String query = "INSERT INTO PAIS (NOMBRE) values (?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {pais.getNombre().toUpperCase()};
		jdbcTemplate.update(query, args);
	}
	
	@Override
	public Pais getById(int id_pais) {
		try{
			String query = "SELECT * FROM PAIS WHERE ID_PAIS = ?";
			jdbcTemplate = new JdbcTemplate(dataSource);
			Pais emp = jdbcTemplate.queryForObject(query, new Object[]{id_pais}, new RowMapper<Pais>(){
				@Override
				public Pais mapRow(ResultSet rs, int rowNum)throws SQLException {
					Pais pais = new Pais();
					pais.setId_pais(rs.getInt("ID_PAIS"));
					pais.setNombre(rs.getString("NOMBRE"));
					return pais;
				}});
			
			return emp;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void update(Pais pais) {
		String query = "UPDATE PAIS SET NOMBRE = ? WHERE ID_PAIS = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {pais.getNombre().toUpperCase(), pais.getId_pais()};
		jdbcTemplate.update(query, args);
	}

	@Override
	public void deleteById(int id_pais) {
		String query = "DELETE P, D, C ";
		query +=       "FROM PAIS P ";
		query +=       "LEFT JOIN DEPARTAMENTO D ON P.ID_PAIS = D.ID_PAIS ";
		query +=       "LEFT JOIN CIUDAD C ON C.ID_DEPARTAMENTO = D.ID_DEPARTAMENTO ";
		query +=       "WHERE P.ID_PAIS = ? ";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, id_pais);
	}

	@Override
	public List<Pais> getAll() {
		String query =  "SELECT P.*, COUNT(D.ID_PAIS) CANTIDAD_DEPARTAMENTOS ";
			   query += "FROM PAIS P ";
			   query += "LEFT JOIN DEPARTAMENTO D ON D.ID_PAIS = P.ID_PAIS ";
			   query += "GROUP BY P.ID_PAIS ";
			   query += "ORDER BY P.NOMBRE ";
		List<Pais> paisList = new ArrayList<Pais>();
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> empRow : empRows){
			Pais pais = new Pais();
			pais.setId_pais(Integer.parseInt(String.valueOf(empRow.get("ID_PAIS"))));
			pais.setNombre(String.valueOf(empRow.get("NOMBRE")));
			pais.setCantidad_departamentos(Integer.parseInt(String.valueOf(empRow.get("CANTIDAD_DEPARTAMENTOS"))));
			paisList.add(pais);
		}
		return paisList;
	}

}
