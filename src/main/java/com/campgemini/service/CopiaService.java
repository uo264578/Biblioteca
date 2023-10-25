package com.campgemini.service;

import java.util.List;

import com.campgemini.model.Copia;

public interface CopiaService {

	Copia getCopiaById(long id);
	List<Copia> getAllCopias();
	void saveCopia(Copia c);
	void deleteCopiaById(long id);
}
