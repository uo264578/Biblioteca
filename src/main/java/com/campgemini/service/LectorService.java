package com.campgemini.service;

import java.util.List;

import com.campgemini.model.Lector;

public interface LectorService {

	Lector getLectorById(long id);
	List<Lector> getAllLectores();
	void saveLector(Lector l);
	void deleteLectorById(long id);
}
