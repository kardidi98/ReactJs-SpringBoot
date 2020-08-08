package com.cap.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cap.spring.boot.entities.Matiere;
import com.cap.spring.boot.entities.Niveau;
import com.cap.spring.boot.jpa.MatiereRepository;
import com.cap.spring.boot.jpa.NiveauRepository;

@Controller
public class MatiereController {
	@Autowired
	private MatiereRepository mr;
	@Autowired
	private NiveauRepository nr;
	
	@RequestMapping(value = "/matieres", method = RequestMethod.GET)
	public String index(Model model) {
		List<Matiere> matieres = mr.findAll();
		model.addAttribute("listMatieres", matieres);
		List<Niveau> niveaux = nr.findAll();
		model.addAttribute("listFournisseurs", niveaux);
		return "Matieres";
	}

	@RequestMapping(value = "/matieres", method = RequestMethod.POST)
	public String ajouter(Model model, Matiere m, Niveau n) {
		m.setNiveau(n);
		mr.saveAndFlush(m);

		List<Matiere> matieres = mr.findAll();
		model.addAttribute("listMatieres", matieres);
		List<Niveau> niveaux = nr.findAll();
		model.addAttribute("listNiveaux", niveaux);
		return "Matieres";
	}
}
