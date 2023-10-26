package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.model.Autor;
import com.capgemini.service.AutorService;

@Controller
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	@PostMapping("/save/autor")
	public String saveAutor(@ModelAttribute("autor") Autor autor) {
		autorService.saveAutor(autor);
		return "redirect:/";
	}
	
	@GetMapping("/delete/autor/{id}")
	public String deleteAutor(@PathVariable(value="id") long id) {
		this.autorService.deleteAutorById(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/autor/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model) {
		Autor autor= this.autorService.getAutorById(id);
		model.addAttribute("autor", autor);
		return "actualizar_autor";
	}
	
	@GetMapping("/add/autor")
	public String showNewAutorForm(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor",autor);
		return "nuevo_autor";
	}
	

}

