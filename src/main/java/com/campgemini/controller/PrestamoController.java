package com.campgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.campgemini.model.Multa;
import com.campgemini.model.Prestamo;
import com.campgemini.service.PrestamoService;

@Controller
public class PrestamoController {
	
	@Autowired
	private PrestamoService prestamoService;
	
	@GetMapping("/")
	private String homePrestamo() {
		
		return null;
		
	}
	
	@GetMapping("/save")
	public String savePrestamo(Prestamo p) {
			this.prestamoService.savePrestamo(p);
		return null;
		}
	
	public String deletePrestamo(long id) {
		this.prestamoService.deletePrestamoById(id);
		return null;
	}
	
	@GetMapping("/update/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Prestamo prestamo=this.prestamoService.getPrestamoById(id);
		model.addAttribute("prestamo", prestamo);
		return "actualizar_prestamo";
	}
	
	@GetMapping("/add")
	public String showNewPrestamoForm(Model model) {
		Prestamo prestamo= new Prestamo();
		model.addAttribute("prestamo", prestamo);
		return "nuevo_prestamo";
}
	
}
