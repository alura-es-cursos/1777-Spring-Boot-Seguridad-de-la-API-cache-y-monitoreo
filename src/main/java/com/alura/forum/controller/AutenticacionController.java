package com.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forum.config.security.AutenticacionService;
import com.alura.forum.controller.dto.TokenDTO;
import com.alura.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {
	
	@Autowired
	private AutenticacionService autenticacionService;
	
	@PostMapping
	public ResponseEntity<?> auth(@RequestBody @Valid LoginForm loginForm) {
		try {
			String token = autenticacionService.autenticarConToken(loginForm);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer "));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
