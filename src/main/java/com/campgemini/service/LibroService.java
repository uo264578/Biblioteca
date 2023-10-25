package com.campgemini.service;

import java.util.List;

import com.campgemini.model.Libro;

public interface LibroService {

	Libro getLibroById(long id);
	List<Libro> getAllLibro();
	void saveLibro(Libro l);
	void deleteLibroById(long id);
}
