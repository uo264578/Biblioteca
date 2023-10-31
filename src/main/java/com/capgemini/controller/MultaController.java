package com.capgemini.controller;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.model.Lector;
import com.capgemini.model.Multa;
import com.capgemini.service.LectorServiceImp;
import com.capgemini.service.MultaService;

@Controller
public class MultaController {

	@Autowired
	private LectorServiceImp lectorService;

	@Autowired
	private MultaService multaService;

	@GetMapping("/multa")
	public String multaForm(Model model) {
		List<Lector> lectores = new ArrayList<>();
		try {
			lectores = lectorService.getAllLectores();
		} catch (ServiceException se) {
		}
		model.addAttribute("lectores", lectores);
		return "multa";
	}

	@PostMapping("/multa")
	public String multarLector(@RequestParam("lectorId") Long lectorId, @RequestParam("dias") int dias, Model model) {
		List<Lector> lectores = new ArrayList<>();
		try {
			lectores = lectorService.getAllLectores();
		} catch (ServiceException se) {
		}

		Lector lector = null;
		try {
			lector = lectorService.getLectorById(lectorId);
		} catch (ServiceException se) {
		}
		
		Multa multa = lector.multar(dias);
		multa.setLector(lector);
		multaService.saveMulta(multa);
		model.addAttribute("message", "Lector multado con éxito");

		model.addAttribute("lectores", lectores);
		return "multa";
	}
}
