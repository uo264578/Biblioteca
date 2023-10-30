package com.capgemini.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.model.EstadoCopia;
import com.capgemini.model.Lector;
import com.capgemini.model.Prestamo;
import com.capgemini.repository.LectorRepository;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LectorService;
import com.capgemini.service.PrestamoService;

@Controller
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private CopiaService copiaService;
	
	@Autowired
	private LectorService lectorService;
	
//	@GetMapping("/")
//	public String viewHomePage() {
//		return "index";
//	}
	
	@RequestMapping(value = "/add/prestamo/{id}", method = RequestMethod.POST)
	public String savePrestamo(Model model, @PathVariable(value="id") long id) {
		Prestamo prestamo = new Prestamo();
		this.copiaService.updatePrestadoCopiaById(id);
		prestamo.setCopia(this.copiaService.getCopiaById(id));
		prestamo.setInicio(LocalDate.now());
		
		Lector lector = this.lectorService.getLectorById(1);//cambiar por usuario
		prestamo.setLector(lector);
		prestamoService.savePrestamo(prestamo);
		
		this.copiaService.getCopiaById(id).setPrestamo(prestamo);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/prestamo/{id}")
	public String deletePrestamo(@PathVariable(value="id") long id) {
		this.prestamoService.deletePrestamoById(id);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/devolver/prestamo/{id}", method = RequestMethod.POST)
	public String showFormForUpdate(@PathVariable(value="id") long idCopia,@PathVariable(value="id") long idPrestamo,Model model) {
		this.copiaService.getCopiaById(idCopia).setEstadoCopia(EstadoCopia.Biblioteca);
		//model.addAttribute("prestamo", prestamo);
		return "redirect:/";
	}
//	
//	@GetMapping("/add/prestamo")
//	public String showNewPrestamoForm(Model model) {
//		Prestamo prestamo = new Prestamo();
//		model.addAttribute("prestamo",prestamo);
//		return "nuevo_prestamo";
//	}
	

}
