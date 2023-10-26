package com.campgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campgemini.model.Prestamo;
import com.campgemini.repository.PrestamoRepository;

@Service
public class PrestamoServImp implements PrestamoService{

	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Override
	public Prestamo getPrestamoById(long id) {
		Optional<Prestamo> optionalPrestamo= this.prestamoRepository.findById(id);
		Prestamo prestamo=null;
		if(optionalPrestamo.isPresent()) {
			prestamo= optionalPrestamo.get();
		}
		else {
			throw new RuntimeException();
		}
		return prestamo;
	}

	@Override
	public List<Prestamo> getAllPrestamos() {
		return this.prestamoRepository.findAll();
	}

	@Override
	public void savePrestamo(Prestamo p) {
		this.prestamoRepository.save(p);
		
	}

	@Override
	public void deletePrestamoById(long id) {
		this.prestamoRepository.deleteById(id);
		
	}

}
