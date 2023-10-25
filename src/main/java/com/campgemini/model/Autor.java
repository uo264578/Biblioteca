package com.campgemini.model;

import java.util.Date;
import java.util.Set;


import jakarta.persistence.*;

@Entity
@Table(name="autores")
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
	
	@OneToMany(mappedBy="autor", targetEntity=Libro.class, cascade=CascadeType.ALL)
	private Set<Libro> libros;
	
}
