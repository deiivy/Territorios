package com.david.territorios.apis;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.david.territorios.entidades.Pais;
import com.david.territorios.managers.PaisManager;
import com.david.territorios.managers.UsuarioManager;
import com.david.territorios.utilidades.JsonTransformer;


@Controller
@RequestMapping(value = "/api")
public class PaisApiController implements Serializable{
	
	private static final long serialVersionUID = 6796791068379547394L;

	private static final Logger logger = LoggerFactory.getLogger(PaisApiController.class);
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private PaisManager paisManager;
	
	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private JsonTransformer jsonTransformer;
	
	@RequestMapping(value = "/Pais", method = RequestMethod.GET, produces = "application/json")
	public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	    try {
	    	
	        List<Pais> paises = paisManager.getAll();
	        String jsonSalida = jsonTransformer.toJson(paises);
	         
	        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	        httpServletResponse.setContentType("application/json; charset=UTF-8");
	        httpServletResponse.getWriter().println(jsonSalida);
	         
	    } catch (Exception ex) {
	        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        logger.error(ex.getMessage());
	    }
	 
	} 
	
	
    @RequestMapping(value = "/Pais/{idPais}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idPais") int idPais) {
        try {
	    	if(!usuarioManager.validarUsuarioRest(httpServletRequest)){
		        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		        httpServletResponse.setContentType("text/html");
		        httpServletResponse.getWriter().println("Error de autenticación");
		        return;
	    	}
        	
            Pais pais = paisManager.getById(idPais);
            String jsonSalida = jsonTransformer.toJson(pais);
             
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
             
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ex.getMessage());
        }
    }
	
    @RequestMapping(value = "/Pais/{idPais}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idPais") int idPais) {
        try {
	    	if(!usuarioManager.validarUsuarioRest(httpServletRequest)){
		        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		        httpServletResponse.setContentType("text/html");
		        httpServletResponse.getWriter().println("Error de autenticación");
		        return;
	    	}
        	
        	Pais pais = (Pais) jsonTransformer.fromJson(jsonEntrada, Pais.class);
        	paisManager.update(pais);
        	
        	Locale currentLocale = httpServletRequest.getLocale();
        	Map<String, Object> datos = new HashMap<String, Object>();
        	datos.put("mensaje", context.getMessage("inicio.actualizar.ok", null, currentLocale));
	        
	        String jsonSalida = jsonTransformer.toJson(datos);
             
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
            	logger.error(ex1.getMessage());            }
        }
    }
    
	@RequestMapping(value = "/Pais", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void insertar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
	    	if(!usuarioManager.validarUsuarioRest(httpServletRequest)){
		        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		        httpServletResponse.setContentType("text/html");
		        httpServletResponse.getWriter().println("Error de autenticación");
		        return;
	    	}
        	
        	Pais pais = (Pais) jsonTransformer.fromJson(jsonEntrada, Pais.class);
        	paisManager.save(pais);
        	
        	Locale currentLocale = httpServletRequest.getLocale();
        	Map<String, Object> datos = new HashMap<String, Object>();
        	datos.put("mensaje", context.getMessage("inicio.guardar.ok", null, currentLocale));
	        
	        String jsonSalida = jsonTransformer.toJson(datos);
             
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
             
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             
        }
	}
	
	@RequestMapping(value = "/Pais/{idPais}", method = RequestMethod.DELETE, produces = "application/json")
	public void eliminar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idPais") int idPais) {
        try {
	    	if(!usuarioManager.validarUsuarioRest(httpServletRequest)){
		        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		        httpServletResponse.setContentType("text/html");
		        httpServletResponse.getWriter().println("Error de autenticación");
		        return;
	    	}
        	
        	paisManager.deleteById(idPais);
            
        	Locale currentLocale = httpServletRequest.getLocale();
        	Map<String, Object> datos = new HashMap<String, Object>();
        	datos.put("mensaje", context.getMessage("inicio.eliminar.ok", null, currentLocale));
	        
	        String jsonSalida = jsonTransformer.toJson(datos);
             
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
             
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             
        }
	}
	
}

