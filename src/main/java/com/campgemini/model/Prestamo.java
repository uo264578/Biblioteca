package com.campgemini.model;

import java.time.LocalDate;

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
@Table(name="prestamo")
public class Prestamo {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate inicio;
	@Column
	private LocalDate fin;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="fk_lector")
	private Lector lector;
	
	@OneToOne
	@JoinColumn(name="fk_copia")
	private Copia copia;
	
	
}
