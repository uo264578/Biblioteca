package com.campgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.campgemini.model.Libro;
import com.campgemini.service.LibroService;

public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1,"nombre","asc",model);
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(
			@PathVariable(value="pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model
			) {
		int pageSize=4;
		Page<Libro> page=libroService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Libro> listLibros=page.getContent();
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortField", sortField);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listLibros", listLibros);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
		
		return "index";
	}

	@PostMapping("/save/libro")
	public String saveLibro(@ModelAttribute("libro") Libro libro) {
		libroService.saveLibro(libro);
		return "redirect:/";
	}
	
	@GetMapping("/delete/libro/{id}")
	public String deleteLibro(@PathVariable(value="id") long id) {
		this.libroService.deleteLibroById(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/libro/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model) {
		Libro libro= this.libroService.getLibroById(id);
		model.addAttribute("libro", libro);
		return "actualizar_libro";
	}
	
	@GetMapping("/add/libro")
	public String showNewLibroForm(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro",libro);
		return "nuevo_libro";
	}
}
