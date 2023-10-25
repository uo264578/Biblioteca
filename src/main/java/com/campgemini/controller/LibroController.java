package com.campgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.campgemini.model.Libro;
import com.campgemini.service.LibroService;

public class LibroController {

	@Autowired
	private LibroService libroService;
	
//	@GetMapping("/")
//	public String viewHomePage(Model model) {
//		return findPaginated(1,"nombre","asc",model);
//	}
	
	@PostMapping("/save")
	public String saveLibro(@ModelAttribute("libro") Libro libro) {
		libroService.saveLibro(libro);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteLibro(@PathVariable(value="id") long id) {
		this.libroService.deleteLibroById(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model) {
		Libro libro= this.libroService.getLibroById(id);
		model.addAttribute("libro", libro);
		return "actualizar_libro";
	}
	
	@GetMapping("/add")
	public String showNewLibroForm(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro",libro);
		return "nuevo_libro";
	}
}
