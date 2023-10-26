package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.model.Libro;
import com.capgemini.repository.LibroRepository;

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

	@Override
	public Page<Libro> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(sortField).ascending():
					Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNum -1, pageSize, sort);
				
		return this.libroRepository.findAll(pageable);
	}

}
