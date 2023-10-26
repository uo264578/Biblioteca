package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Autor;

public interface AutorService {

	Autor getAutorById(long id);
	List<Autor> getAllAutores();
	void saveAutor(Autor a);
	void deleteAutorById(long id);
}
