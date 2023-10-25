package com.campgemini.service;

import java.util.List;

import com.campgemini.model.Multa;

public interface MultaService {

	Multa getMultaById(long id);
	List<Multa> getAllMultas();
	void saveMulta(Multa m);
	void deleteMultaById(long id);
}
