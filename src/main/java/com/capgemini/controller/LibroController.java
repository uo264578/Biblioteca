package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.model.Autor;
import com.capgemini.model.Copia;
import com.capgemini.model.EstadoCopia;
import com.capgemini.model.Libro;
import com.capgemini.service.AutorService;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LibroService;

@Controller
public class LibroController {

	@Autowired
	private AutorService autorService;
	@Autowired
	private LibroService libroService;
	@Autowired
	private CopiaService copiaService;
	
//	@GetMapping("/")
//	public String viewHomePage(Model model) {
//		return findPaginated(1,"titulo","asc",model);
//	}
	
//	@GetMapping("/page/{pageNo}")
//	public String findPaginated(
//			@PathVariable(value="pageNo") int pageNo,
//			@RequestParam("sortField") String sortField,
//			@RequestParam("sortDir") String sortDir,
//			Model model
//			) {
//		int pageSize=4;
//		Page<Libro> page=libroService.findPaginated(pageNo, pageSize, sortField, sortDir);
//		//List<Libro> listLibros=page.getContent();
//		List<Libro> listLibros=libroService.getAllLibro();
//		
//		
//		model.addAttribute("sortDir", sortDir);
//		model.addAttribute("sortField", sortField);
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("num", listLibros.size());
//		model.addAttribute("listLibros", listLibros);
//		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
//		
//		return "index";
//	}

	@PostMapping("/save/libro")
	public String saveLibro(@ModelAttribute("libro") Libro libro) {
		Autor autor = new Autor();
		Copia copia = new Copia();
		autor.setNombre(libro.getAutor().getNombre());
		autor.setNacionalidad(libro.getAutor().getNacionalidad());
		autor.setFechaNacimiento(libro.getAutor().getFechaNacimiento());
		autorService.saveAutor(autor);
		libro.setAutor(autor);
		libroService.saveLibro(libro);
		copia.setEstadoCopia(EstadoCopia.Biblioteca);
		copia.setLibro(libro);
		copiaService.saveCopia(copia);
		
		
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
