package com.campgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.campgemini.model.Lector;
import com.campgemini.service.LectorService;

@Controller
public class LectorController {
	
	@Autowired
	private LectorService lectorService;
	
	@GetMapping("/")
	public String homeLector() {
		return null;
		
	}
	@GetMapping("/save")
	public String saveLector(Lector l) {
		this.lectorService.saveLector(l);
		
		return "redirect";
	}
	@GetMapping("/delete")
	public String deleteLector(long id) {
		this.lectorService.deleteLectorById(id);
		return null;
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Lector lector=this.lectorService.getLectorById(id);
		model.addAttribute("lector", lector);
		return "actualizar_lector";
	}
	
	@GetMapping("/add")
	public String showNewLectorForm(Model model) {
		Lector lector= new Lector();
		model.addAttribute("lector", lector);
		return "nuevo_lector";
}
}
