package com.campgemini.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="copia")
public class Copia extends Libro{

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private EstadoCopia estadoCopia;
	
	@Column
	private Prestamo prestamo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_libro")
	private Libro libro;
	
}
