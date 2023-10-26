package com.campgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.campgemini.model.Copia;
import com.campgemini.service.CopiaService;

public class CopiaController {

	@Autowired
	private CopiaService copiaService;
	
//	@GetMapping("/")
//	public String viewHomePage(Model model) {
//		return findPaginated(1,"nombre","asc",model);
//	}
	
	@PostMapping("/save/copia")
	public String saveCopia(@ModelAttribute("copia") Copia libro) {
		copiaService.saveCopia(libro);
		return "redirect:/";
	}
	
	@GetMapping("/delete/copia/{id}")
	public String deleteCopia(@PathVariable(value="id") long id) {
		this.copiaService.deleteCopiaById(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/copia/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model) {
		Copia copia= this.copiaService.getCopiaById(id);
		model.addAttribute("copia", copia);
		return "actualizar_copia";
	}
	
	@GetMapping("/add/copia")
	public String showNewCopiaForm(Model model) {
		Copia copia = new Copia();
		model.addAttribute("copia",copia);
		return "nuevo_copia";
	}
}
