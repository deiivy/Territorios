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

import com.david.territorios.entidades.Ciudad;
import com.david.territorios.managers.CiudadManager;
import com.david.territorios.utilidades.JsonTransformer;


@Controller
@RequestMapping(value = "/api")
public class CiudadApiController implements Serializable{
	
	private static final long serialVersionUID = -6823870525853736883L;

	private static final Logger logger = LoggerFactory.getLogger(CiudadApiController.class);
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private CiudadManager ciudadManager;
	
	@Autowired
	private JsonTransformer jsonTransformer;
	
	@RequestMapping(value = "/Ciudad", method = RequestMethod.GET, produces = "application/json")
	public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	    try {
	    	
	        List<Ciudad> ciudad = ciudadManager.getAll();
	        String jsonSalida = jsonTransformer.toJson(ciudad);
	         
	        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	        httpServletResponse.setContentType("application/json; charset=UTF-8");
	        httpServletResponse.getWriter().println(jsonSalida);
	         
	    } catch (Exception ex) {
	        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        logger.error(ex.getMessage());
	    }
	 
	} 
	
	
    @RequestMapping(value = "/Ciudad/{idCiudad}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idCiudad") int idCiudad) {
        try {
        	Ciudad ciudad = ciudadManager.getById(idCiudad);
            String jsonSalida = jsonTransformer.toJson(ciudad);
             
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
             
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ex.getMessage());
        }
    }
	
    @RequestMapping(value = "/Ciudad/{idCiudad}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idCiudad") int idCiudad) {
        try {
        	Ciudad ciudad = (Ciudad) jsonTransformer.fromJson(jsonEntrada, Ciudad.class);
        	ciudadManager.update(ciudad);
            
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
    
	@RequestMapping(value = "/Ciudad", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void insertar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
        	Ciudad ciudad = (Ciudad) jsonTransformer.fromJson(jsonEntrada, Ciudad.class);
        	ciudadManager.save(ciudad);
            
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
	
	@RequestMapping(value = "/Ciudad/{idCiudad}", method = RequestMethod.DELETE, produces = "application/json")
	public void eliminar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idCiudad") int idCiudad) {
        try {
        	ciudadManager.deleteById(idCiudad);
            
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

