package com.campgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campgemini.model.Copia;
import com.campgemini.repository.CopiaRepository;

@Service
public class CopiaServImp implements CopiaService{
	
	@Autowired
	private CopiaRepository copiaRepository;

	@Override
	public Copia getCopiaById(long id) {
		Optional<Copia> optionalCopia= this.copiaRepository.findById(id);
		Copia copia= null;
		if(optionalCopia.isPresent()) {
			copia= optionalCopia.get();
		}else {
			throw new RuntimeException();
		}
		return copia;
	}

	@Override
	public List<Copia> getAllCopias() {
		
		return this.copiaRepository.findAll();
	}

	@Override
	public void saveCopia(Copia a) {
		this.copiaRepository.save(a);	
	}

	@Override
	public void deleteCopiaById(long id) {
		this.copiaRepository.deleteById(id);
		
	}

}
