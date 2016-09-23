package com.david.territorios.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.david.territorios.entidades.Usuario;
import com.david.territorios.managers.UsuarioManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UsuarioManager usuarioManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView mainPage(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
        Object user = session.getAttribute("usuario");
        
        if(user == null){
        	return new ModelAndView("login");
    	}
		
        return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, Model model, @ModelAttribute("Usuario") Usuario usuarioValidar) {
		Usuario usuario = usuarioManager.validarUsuario(usuarioValidar);
		
		if(usuario == null){
			model.addAttribute("msg", "Datos erroneos");
			return new ModelAndView("login");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			session.setMaxInactiveInterval(20*60);		
			
			return new ModelAndView("redirect:/home");
		}
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("usuario");
        
        if(user == null){
        	return "login";
    	}
        
		return "home";
	}
	
	@RequestMapping(value = "/error_403", method = RequestMethod.GET)
	public String error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("text/html");
		return "403";
	}
}
