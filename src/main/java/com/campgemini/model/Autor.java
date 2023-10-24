package com.campgemini.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="autor")
public class Autor {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String nacionalidad;
	
	@Column
	private Date fechaNacimiento;
	
	
}
