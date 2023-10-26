package com.campgemini.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="lector")
public class Lector {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long nSocio;
	
	@Column
	private String nombre;

	@Column
	private String telefono;

	@Column
	private String direccion;
	
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="fk_multa")
	private Multa multa;
	
	@OneToMany(mappedBy="lector", targetEntity=Prestamo.class, cascade=CascadeType.ALL)
	private Set<Prestamo> prestamos;
}
