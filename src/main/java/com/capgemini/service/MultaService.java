package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Multa;

public interface MultaService {

	Multa getMultaById(long id);
	List<Multa> getAllMultas();
	void saveMulta(Multa a);
	void deleteMultaById(long id);
}
