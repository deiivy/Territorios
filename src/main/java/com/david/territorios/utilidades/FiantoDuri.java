package com.david.territorios.utilidades;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.david.territorios.managers.UsuarioManager;


/**
 *
 * @author Maick
 */
@WebFilter(filterName = "FiantoDuri", urlPatterns = {"/api/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.REQUEST, DispatcherType.INCLUDE})
public class FiantoDuri implements Filter {
    
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    
    public FiantoDuri() {
    }    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("FiantoDuri:doFilter()");
        }
            
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse)response;
            
        HttpSession session = req.getSession();
        Object user = session.getAttribute("usuario");
         
        if(user != null){
        	chain.doFilter(request, response);
        	return;
        }
            
        UsuarioManager usuarioManager = ApplicationContextHolder.getContext().getBean(UsuarioManager.class);
        boolean existeUsuario = usuarioManager.validarUsuarioRest(req);
            
	    if(!existeUsuario){
	    	RequestDispatcher rd = req.getRequestDispatcher("/error_403");
			rd.forward(request, response);
		    return;
	    }else{
	    	chain.doFilter(request, response);
	    }
    }

    /**
     * Return the filter configuration object for this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FiantoDuri:Initializing filter");
            }
        }
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
}
