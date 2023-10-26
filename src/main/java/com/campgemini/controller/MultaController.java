package com.campgemini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.campgemini.model.Multa;
import com.campgemini.model.Prestamo;
import com.campgemini.service.MultaService;

@Controller
public class MultaController {
	
	private MultaService multaService;
	
	@PostMapping("/save/multa")
	public String saveMulta(@ModelAttribute("prestamo") Multa multa) {
		multaService.saveMulta(multa);
		return "redirect:/";
	}
	
	@GetMapping("/delete/multa/{id}")
	public String deleteMulta(@PathVariable(value="id") long id) {
		this.multaService.deleteMultaById(id);
		return "redirect:/";
	}
}
