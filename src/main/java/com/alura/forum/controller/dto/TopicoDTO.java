package com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.alura.forum.model.Topico;

public class TopicoDTO {

	private Long id;
	
	private String titulo;
	
	private String mensaje;
	
	private LocalDateTime fechaCreacion;
	
	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensaje = topico.getMensaje();
		this.fechaCreacion = topico.getFechaCreacion();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public static Page<TopicoDTO> convertir(Page<Topico> topicos) {
		return topicos.map(TopicoDTO::new);
	}
	
}
