package com.campgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.campgemini.model.Autor;


public class AutorController {

	@Autowired
	private AutorService AutorService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1,"nombreCurso","asc",model);
	}
	
	@PostMapping("/save")
	public String saveCurso(@ModelAttribute("autor") Autor autor) {
		autorService.saveAutor(autor);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCurso(@PathVariable(value="id") long id) {
		this.cursoService.deleteCursoById(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model) {
		Curso curso= this.cursoService.getCursoById(id);
		model.addAttribute("curse", curso);
		return "actualizar_curso";
	}
	
	@GetMapping("/add")
	public String showNewCursoForm(Model model) {
		Curso curso = new Curso();
		model.addAttribute("curse",curso);
		return "nuevo_curso";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(
			@PathVariable(value="pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model
			) {
		int pageSize=4;
		Page<Curso> page=cursoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Curso> listCursos=page.getContent();
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortField", sortField);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listCurses", listCursos);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
		
		return "index";
	}
}

