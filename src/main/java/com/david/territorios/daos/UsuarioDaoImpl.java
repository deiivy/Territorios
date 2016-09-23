package com.david.territorios.daos;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Departamento;
import com.david.territorios.entidades.Pais;
import com.david.territorios.entidades.Usuario;

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
public class UsuarioDaoImpl implements UsuarioDao{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Usuario validarUsuario(Usuario usuarioBuscar) {
		try{
			String query =  "SELECT * FROM USUARIO WHERE USUARIO = ? AND CLAVE = ? ";
			jdbcTemplate = new JdbcTemplate(dataSource);
			Usuario user = jdbcTemplate.queryForObject(query, new Object[]{usuarioBuscar.getUsuario(), usuarioBuscar.getClave()}
													, new RowMapper<Usuario>(){
				public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
					Usuario usuario = new Usuario();
					usuario.setId_usuario(rs.getInt("ID_USUARIO"));
					usuario.setUsuario(rs.getString("USUARIO"));
					usuario.setClave(rs.getString("CLAVE"));
					return usuario;
				}});
			
			return user;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
