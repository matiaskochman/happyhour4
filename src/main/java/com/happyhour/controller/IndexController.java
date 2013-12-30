package com.happyhour.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happyhour.service.UsuarioService;

@Controller
@RequestMapping
public class IndexController {
	protected static Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request){
		
		String username = usuarioService.getLoggedUserName();	    
		request.getSession().setAttribute("username", username);
	    
		return "index";
	}
}
