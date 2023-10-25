package com.campgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campgemini.model.Libro;
import com.campgemini.repository.LibroRepository;

@Service
public class LibroServImp implements LibroService{
	
	@Autowired
	private LibroRepository libroRepository;

	@Override
	public Libro getLibroById(long id) {
		Optional<Libro> optionalLibro= this.libroRepository.findById(id);
		Libro libro= null;
		if(optionalLibro.isPresent()) {
			libro= optionalLibro.get();
		}else {
			throw new RuntimeException();
		}
		return libro;
	}

	@Override
	public List<Libro> getAllLibro() {
		
		return this.libroRepository.findAll();
	}

	@Override
	public void saveLibro(Libro a) {
		this.libroRepository.save(a);	
	}

	@Override
	public void deleteLibroById(long id) {
		this.libroRepository.deleteById(id);
		
	}

}
