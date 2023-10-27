package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.capgemini.model.Copia;
import com.capgemini.service.CopiaService;

@Controller
public class CopiaController {

	@Autowired
	private CopiaService copiaService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1,"libro.titulo","asc",model);
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(
			@PathVariable(value="pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model
			) {
		int pageSize=4;
		Page<Copia> page=copiaService.findPaginated(pageNo, pageSize, sortField, sortDir);
		//List<Libro> listLibros=page.getContent();
		List<Copia> listCopias=copiaService.getAllCopias();
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		    if (authentication != null && authentication.getAuthorities() != null) {
		        for (GrantedAuthority authority : authentication.getAuthorities()) {
		            if ("ROLE_ADMIN".equals(authority.getAuthority())) {
		                model.addAttribute("isAdmin", true);
		            }
		        }
		    }
		
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortField", sortField);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("num", listCopias.size());
		model.addAttribute("listCopias", listCopias);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
		
		return "index";
	}
	
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