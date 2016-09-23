package com.david.territorios.managers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.david.territorios.entidades.Pais;
import com.david.territorios.entidades.Usuario;

@Service
public interface UsuarioManager {
	
	boolean validarUsuarioRest(HttpServletRequest httpServletRequest);
	
	Usuario validarUsuario(Usuario usuario);
	
}
