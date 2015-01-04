package com.happyhour.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.happyhour.service.UsuarioService;

@Component("AuthenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	private UsuarioService service;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		String user = service.getLoggedUserName();
		String ip = request.getRemoteAddr();
		request.getSession().setAttribute("ipUser", ip);
		request.getSession().setAttribute("loggedUserId", user);
		super.onAuthenticationSuccess(request, response, auth);
	}
}
