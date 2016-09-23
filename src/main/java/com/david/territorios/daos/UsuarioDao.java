package com.david.territorios.daos;

import org.springframework.stereotype.Repository;

import com.david.territorios.entidades.Usuario;


@Repository
public interface UsuarioDao {

	Usuario validarUsuario(Usuario usuario);
	
}
