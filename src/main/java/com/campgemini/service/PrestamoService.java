package com.campgemini.service;

import java.util.List;

import com.campgemini.model.Prestamo;

public interface PrestamoService {

	Prestamo getPrestamoById(long id);
	List<Prestamo> getAllPrestamos();
	void savePrestamo(Prestamo p);
	void deletePrestamoById(long id);
	
}
