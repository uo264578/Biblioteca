package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Copia;
import com.capgemini.model.EstadoCopia;
import com.capgemini.repository.CopiaRepository;

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

	@Override
	public Page<Copia> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(sortField).ascending():
					Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNum -1, pageSize, sort);
				
		return this.copiaRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public void updatePrestadoCopiaById(long id) {
		this.copiaRepository.getById(id).setEstadoCopia(EstadoCopia.Prestado);
	}

	@Transactional
	@Override
	public void updateDevueltoCopiaById(long id) {
		this.copiaRepository.getById(id).setEstadoCopia(EstadoCopia.Biblioteca);
	}

}
