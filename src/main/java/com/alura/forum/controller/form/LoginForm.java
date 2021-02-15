package com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class LoginForm {

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String contrasena;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Authentication convertir() {
		return new UsernamePasswordAuthenticationToken(email, contrasena);
	}

}
