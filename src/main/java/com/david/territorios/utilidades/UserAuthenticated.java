package com.david.territorios.utilidades;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.david.territorios.entidades.Usuario;

public class UserAuthenticated {

	public static Usuario validarDatosRest(HttpServletRequest httpServletRequest) {
		try {
			String header = httpServletRequest.getHeader("Authorization");
			
			if(header == null){
				return null;
			}
			
	    	String datos = header.substring(header.indexOf(" ") + 1 );
	        
	    	byte[] bytes = Base64.getDecoder().decode(datos); 
	    	String decoded = new String(bytes);
	    	
	    	String user = decoded.substring(0, decoded.indexOf(":"));
	    	String clave = decoded.substring(decoded.indexOf(":") + 1);
	    	
	    	if(user.isEmpty() || clave.isEmpty()){
	    		return null;
	    	}
	    	
	    	Usuario usuario = new Usuario();
	    	usuario.setUsuario(user);
	    	usuario.setClave(encode(clave));
	    	
			return usuario;
		} catch (Exception e) {
			return null;
		}
	}
	
    public static String encode(String cadena){
    	return DigestUtils.md5Hex(cadena); 
    }
}
