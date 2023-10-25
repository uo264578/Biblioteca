package com.campgemini.service;

import java.util.List;

import com.campgemini.model.Autor;

public interface AutorService {

	Autor getAutorById(long id);
	List<Autor> getAllAutores();
	void saveAutor(Autor a);
	void deleteAutorById(long id);
}
