package com.alura.forum.config.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alura.forum.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expirationTime}")
	private Long expirationTime;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	@Value("${forum.jwt.issuer}")
	private String issuer;

	public String generarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		LocalDateTime ahora = LocalDateTime.now();
		LocalDateTime expiracion = ahora.plusMinutes(expirationTime);
		
		return Jwts.builder()
				.setIssuer(issuer)
				.setSubject(usuario.getId().toString())
				.setIssuedAt(Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(Date.from(expiracion.atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
