package com.capgemini.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.model.EstadoCopia;
import com.capgemini.model.Lector;
import com.capgemini.model.Multa;
import com.capgemini.model.Prestamo;
import com.capgemini.repository.LectorRepository;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LectorService;
import com.capgemini.service.MultaService;
import com.capgemini.service.PrestamoService;

@Controller
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private CopiaService copiaService;
	
	@Autowired
	private LectorService lectorService;
	
	@Autowired
	private MultaService multaService;
	
//	@GetMapping("/")
//	public String viewHomePage() {
//		return "index";
//	}
	@Transactional
	@PostMapping("/add/prestamo/{id}")
	public String savePrestamo( @RequestParam(name = "lectorId") long lectorId, Model model,
			@PathVariable(value="id") long id) {
		
		Set<Prestamo> prestamos= this.lectorService.getLectorById(lectorId).getPrestamos();
		Multa multaLector= this.lectorService.getLectorById(lectorId).getMulta();
		if(prestamos.size()<3 && (multaLector == null || LocalDate.now().isAfter(multaLector.getFin()))) {
		Prestamo prestamo = new Prestamo();
		this.copiaService.updatePrestadoCopiaById(id);
		prestamo.setCopia(this.copiaService.getCopiaById(id));
		prestamo.setInicio(LocalDate.now());
		prestamo.setFin(LocalDate.now().plusDays(1));
		
		Lector lector = this.lectorService.getLectorById(lectorId);//cambiar por usuario
		prestamo.setLector(lector);
		this.prestamoService.savePrestamo(prestamo);
		
		this.copiaService.getCopiaById(id).setPrestamo(this.prestamoService.getPrestamoById(prestamo.getId()));
		}
		else {
		
			model.addAttribute("error", "Ya tienes 3 libros prestados o una multa pendiente");
		}
		return "redirect:/";
	}
	
	@GetMapping("/delete/prestamo/{id}")
	public String deletePrestamo(@PathVariable(value="id") long id) {
		this.prestamoService.deletePrestamoById(id);
		return "redirect:/";
	}
	
	@Transactional
	@PostMapping("/devolver/prestamo/{id}")
	public String devolverCopia(@PathVariable(value="id") long idCopia, Model model) {
	    this.copiaService.updateDevueltoCopiaById(idCopia);
	    Prestamo prestamo = this.copiaService.getCopiaById(idCopia).getPrestamo();
	    Lector lectorPrestamo = this.prestamoService.getPrestamoById(prestamo.getId()).getLector();
	    this.copiaService.getCopiaById(idCopia).setPrestamo(null);
	    this.prestamoService.deletePrestamoById(prestamo.getId());
	    long diasTranscurridos = ChronoUnit.DAYS.between(prestamo.getFin(), LocalDate.now());
	    if(diasTranscurridos >0) {
	    	Multa multa = lectorPrestamo.multar((int)diasTranscurridos*2);
	    	multa.setLector(lectorPrestamo);
			multaService.saveMulta(multa);
			
	    }
	    model.addAttribute("lectorPrestamo", lectorPrestamo);

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
