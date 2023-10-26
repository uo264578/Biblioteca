package com.campgemini.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


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
	private LocalDate fechaNacimiento;
	
	@OneToMany(mappedBy="autor", targetEntity=Libro.class, cascade=CascadeType.ALL)
	private Set<Libro> libros;
	
}
