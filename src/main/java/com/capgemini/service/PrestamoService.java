package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Prestamo;

public interface PrestamoService {

	Prestamo getPrestamoById(long id);
	List<Prestamo> getAllPrestamos();
	void savePrestamo(Prestamo p);
	void deletePrestamoById(long id);
	
}
