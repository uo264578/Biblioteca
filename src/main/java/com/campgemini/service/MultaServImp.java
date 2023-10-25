package com.campgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campgemini.model.Multa;
import com.campgemini.repository.MultaRepository;

@Service
public class MultaServImp implements MultaService{
	
	@Autowired
	private MultaRepository multaRepository;

	@Override
	public Multa getMultaById(long id) {
		Optional<Multa> optionalMulta= this.multaRepository.findById(id);
		Multa multa= null;
		if(optionalMulta.isPresent()) {
			multa= optionalMulta.get();
		}else {
			throw new RuntimeException();
		}
		return multa;
	}

	@Override
	public List<Multa> getAllMultas() {
		
		return this.multaRepository.findAll();
	}

	@Override
	public void saveMulta(Multa a) {
		this.multaRepository.save(a);	
	}

	@Override
	public void deleteMultaById(long id) {
		this.multaRepository.deleteById(id);
		
	}

}
