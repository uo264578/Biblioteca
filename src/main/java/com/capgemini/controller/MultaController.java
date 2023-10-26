package com.capgemini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.model.Multa;
import com.capgemini.model.Prestamo;
import com.capgemini.service.MultaService;

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
