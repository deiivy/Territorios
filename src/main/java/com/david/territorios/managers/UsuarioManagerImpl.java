package com.david.territorios.managers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.david.territorios.daos.UsuarioDao;
import com.david.territorios.entidades.Usuario;
import com.david.territorios.utilidades.UserAuthenticated;


@Service
public class UsuarioManagerImpl implements UsuarioManager {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public boolean validarUsuarioRest(HttpServletRequest httpServletRequest) {
		try {
			Usuario usuarioBuscar = UserAuthenticated.validarDatosRest(httpServletRequest);
			if(usuarioBuscar == null){
				return false;
			}
			
			Usuario usuario = usuarioDao.validarUsuario(usuarioBuscar);
			if(usuario == null){
				return false;
			}
	    	
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Usuario validarUsuario(Usuario usuario) {
		try {
			
			usuario.setClave(UserAuthenticated.encode(usuario.getClave()));
			
			return usuarioDao.validarUsuario(usuario);
		} catch (Exception e) {
			return null;
		}
	}
}
