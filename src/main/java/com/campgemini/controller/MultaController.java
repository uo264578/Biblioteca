package com.campgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.campgemini.model.Multa;
import com.campgemini.service.MultaService;

@Controller
public class MultaController {

	@Autowired
	private MultaService multaService;
	
	@GetMapping("/")
	public String homeMultas() {
		return null;
		
	}
	
	@GetMapping("/save")
	public String saveMulta(Multa m) {
		this.multaService.saveMulta(m);
		return "redirect:/";
	}
	
	public String deleteMulta(long id) {
		this.multaService.deleteMultaById(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Multa multa=this.multaService.getMultaById(id);
		model.addAttribute("multa", multa);
		return "actualizar_multa";
	}
	
	@GetMapping("/add")
	public String showNewMultaForm(Model model) {
		Multa multa= new Multa();
		model.addAttribute("multa", multa);
		return "nuevo_multa";
}
}
