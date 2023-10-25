package com.campgemini.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="libro")
public class Libro{

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String titulo;
	
	@Column
	private TipoLibro tipo;
	
	@Column
	private String editorial;
	
	@Column
	private int año;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_autor")
	private Autor autor;
	
	@OneToMany(mappedBy="libro", targetEntity=Copia.class, cascade=CascadeType.ALL)
	private Set<Copia> copias;
}
