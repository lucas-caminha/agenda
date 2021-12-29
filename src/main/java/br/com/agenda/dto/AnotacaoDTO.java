package br.com.agenda.dto;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agenda.entity.AnotacaoEntity;

public class AnotacaoDTO {
	
	@Size(max = 50)
	private String titulo;
	private String texto;
	private LocalDate data;
	@JsonIgnore
	private Integer id;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "AnotacaoDTO [titulo=" + titulo + ", texto=" + texto + ", data=" + data + "]";
	}
	
	public AnotacaoEntity toEntity() {
		AnotacaoEntity entity = new AnotacaoEntity();
		entity.setTitulo(this.titulo);
		entity.setTexto(this.texto);
		entity.setData(this.data);
		return entity;
	}

}
