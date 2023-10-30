package com.capgemini.model;

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
@Table(name="")
public class Multa {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate fInicio;
	@Column
	private LocalDate fFin;
	
	@OneToOne
	private Lector lector;
	

	public Multa(LocalDate fInicio, LocalDate fFin, Lector lector) {
		super();
		this.fInicio = fInicio;
		this.fFin = fFin;
		this.lector = lector;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getInicio() {
		return fInicio;
	}
	public void setInicio(LocalDate inicio) {
		this.fInicio = inicio;
	}
	public LocalDate getFin() {
		return fFin;
	}
	public void setFin(LocalDate fin) {
		this.fFin = fin;
	}
	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector2) {
		this.lector = lector2;
	}
	
}
