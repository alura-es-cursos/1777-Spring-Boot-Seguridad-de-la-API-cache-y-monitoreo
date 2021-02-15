package com.alura.forum.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.forum.model.Usuario;
import com.alura.forum.repository.UsuarioRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class AutenticacionPorTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;

	public AutenticacionPorTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		validaAndAutenticaCliente(token);
		
		filterChain.doFilter(request, response);
	}

	private void validaAndAutenticaCliente(String token) {
		Optional<Jws<Claims>> optClaims = tokenService.getTokenInfo(token);

		optClaims.ifPresent(claims -> {
			long idUsuario = Long.parseLong(claims.getBody().getSubject());

			Usuario usuario = usuarioRepository.findById(idUsuario).get();

			UsernamePasswordAuthenticationToken autenticacionPorToken = new UsernamePasswordAuthenticationToken(usuario,
					null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(autenticacionPorToken);
		});
	}

	private String recuperarToken(HttpServletRequest request) {
		String tokenHeader = request.getHeader("Authorization");
		String comienzoToken = "Bearer ";
		
		if (tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith(comienzoToken)) {
			return null;
		}
		
		return tokenHeader.substring(comienzoToken.length() , tokenHeader.length());
	}

}
