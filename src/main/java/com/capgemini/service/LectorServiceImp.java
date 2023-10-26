package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Lector;
import com.capgemini.repository.LectorRepository;

@Service
public class LectorServiceImp implements LectorService{
	
	@Autowired
	private LectorRepository lectorRepository;

	@Override
	public Lector getLectorById(long id) {
		Optional<Lector> optionalLector= this.lectorRepository.findById(id);
		Lector lector= null;
		if(optionalLector.isPresent()) {
			lector= optionalLector.get();
		}else {
			throw new RuntimeException();
		}
		return lector;
	}

	@Override
	public List<Lector> getAllLectores() {
		
		return this.lectorRepository.findAll();
	}

	@Override
	public void saveLector(Lector a) {
		this.lectorRepository.save(a);	
	}

	@Override
	public void deleteLectorById(long id) {
		this.lectorRepository.deleteById(id);
		
	}

}
