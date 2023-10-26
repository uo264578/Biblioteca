package com.campgemini.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.campgemini.model.Libro;

public interface LibroService {

	Libro getLibroById(long id);
	List<Libro> getAllLibro();
	void saveLibro(Libro l);
	void deleteLibroById(long id);
	
	Page<Libro> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
}
