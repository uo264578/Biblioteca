package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Autor;
import com.capgemini.repository.AutorRepository;

@Service
public class AutorServImp implements AutorService{
	
	@Autowired
	private AutorRepository autorRepository;

	@Override
	public Autor getAutorById(long id) {
		Optional<Autor> optionalAutor= this.autorRepository.findById(id);
		Autor autor= null;
		if(optionalAutor.isPresent()) {
			autor= optionalAutor.get();
		}else {
			throw new RuntimeException();
		}
		return autor;
	}

	@Override
	public List<Autor> getAllAutores() {
		
		return this.autorRepository.findAll();
	}

	@Override
	public void saveAutor(Autor a) {
		this.autorRepository.save(a);	
	}

	@Override
	public void deleteAutorById(long id) {
		this.autorRepository.deleteById(id);
		
	}

}
