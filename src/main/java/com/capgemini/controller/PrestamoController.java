package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.model.Prestamo;
import com.capgemini.service.PrestamoService;

@Controller
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	
//	@GetMapping("/")
//	public String viewHomePage() {
//		return "index";
//	}
	
	@PostMapping("/save/prestamo")
	public String savePrestamo(@ModelAttribute("prestamo") Prestamo prestamo) {
		prestamoService.savePrestamo(prestamo);
		return "redirect:/";
	}
	
	@GetMapping("/delete/prestamo/{id}")
	public String deletePrestamo(@PathVariable(value="id") long id) {
		this.prestamoService.deletePrestamoById(id);
		return "redirect:/";
	}
	
	@GetMapping("/update/prestamo/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model) {
		Prestamo prestamo= this.prestamoService.getPrestamoById(id);
		model.addAttribute("prestamo", prestamo);
		return "actualizar_prestamo";
	}
	
	@GetMapping("/add/prestamo")
	public String showNewPrestamoForm(Model model) {
		Prestamo prestamo = new Prestamo();
		model.addAttribute("prestamo",prestamo);
		return "nuevo_prestamo";
	}
	

}
