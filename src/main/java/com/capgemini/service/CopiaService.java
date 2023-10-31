package com.capgemini.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.model.Copia;
import com.capgemini.model.Libro;

public interface CopiaService {

	Copia getCopiaById(long id);
	List<Copia> getAllCopias();
	void saveCopia(Copia c);
	void deleteCopiaById(long id);
	void updatePrestadoCopiaById(long id);
	void updateDevueltoCopiaById(long id);
	
	Page<Copia> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
	void reparaCopiaById(long idCopia);
}
