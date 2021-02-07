package com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacionService autenticacionService;

	// Configuraciones de autenticación
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacionService)
					.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// Configuraciones de autorización de acceso
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/topicos").permitAll()
					.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
					.anyRequest().authenticated()
					.and().formLogin();
	}
	
	// Configuraciones de recursos estáticos (js, css, .png...)
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
