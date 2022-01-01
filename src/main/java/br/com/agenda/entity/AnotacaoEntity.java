package br.com.agenda.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.agenda.dto.AnotacaoDTO;

@Entity(name = "anotacao")
@Table(name = "anotacao")
public class AnotacaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "texto")
	private String texto;
	@Column(name = "data")
	private LocalDate data;
	
	public AnotacaoEntity() {
	}
	
	public AnotacaoEntity(Integer id, String titulo, String texto, LocalDate data) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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

	@Override
	public String toString() {
		return "Anotacao [id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", data=" + data + "]";
	}

	public AnotacaoDTO toDTO() {
		AnotacaoDTO dto = new AnotacaoDTO();
		dto.setId(this.id);
		dto.setTitulo(this.titulo);
		dto.setTexto(this.texto);
		dto.setData(this.data);
		return dto;
	}
	
	
	
}
