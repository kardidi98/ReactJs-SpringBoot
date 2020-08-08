package com.cap.spring.boot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.spring.boot.entities.Niveau;
import com.cap.spring.boot.jpa.NiveauRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class NiveauController {

	@Autowired
	NiveauRepository nr;

	public String service(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size,
			@RequestParam(name = "nom", defaultValue = "") String nom,
			@RequestParam(name = "abr", defaultValue = "") String abr){
		Page<Niveau> pageNiveaux;
		if(nom==null && abr ==null)
			pageNiveaux = nr.findAll(PageRequest.of(page, size));
		else
			pageNiveaux = nr.findNiveauByNom("%" + nom + "%", PageRequest.of(page, size));
		model.addAttribute("listNiveaux", pageNiveaux.getContent());
		int[] pages = new int[pageNiveaux.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		model.addAttribute("nom", nom);
		model.addAttribute("abr", abr);
		return "Niveaux";
		
	}
	
	@RequestMapping(value = "/niveaux", method = RequestMethod.GET)
	public String indexPage(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		
		return service(model, page, size, null, null);
	}
	
	@RequestMapping(value = "/niveaux",  method = RequestMethod.POST)
	public String indexPage(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "nom", defaultValue = "") String nom,
			@RequestParam(name = "abr", defaultValue = "") String abr) {
		return service(model, page, size, nom, abr);
	}

	@RequestMapping(value = "/ajouterNiveau", method = RequestMethod.POST)
	public String AjouterNiveau(Model model, @Valid Niveau f,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "forward:/ajouterNiveau";
		nr.save(f);
		return "redirect:/niveaux";
	}

	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/niveaux";
	}
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/403")
	public String AccessRefuger() {
		return "403";
	}
}
