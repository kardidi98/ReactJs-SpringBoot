package com.cap.spring.boot.service.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.spring.boot.entities.Matiere;
import com.cap.spring.boot.entities.Niveau;
import com.cap.spring.boot.jpa.MatiereRepository;
import com.cap.spring.boot.jpa.NiveauRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowedHeaders = "*",maxAge = 3600)
public class MatiereRestService {

	@Autowired
	MatiereRepository nr;
	@Autowired
	NiveauRepository nivRep;

	@RequestMapping(value = "/matiereRestFull", method = RequestMethod.GET)
	public List<Matiere> listMatieres() {
		return nr.findAll();
	}

	@RequestMapping(value = "/matiereRestFull/{id}", method = RequestMethod.GET)
	public Matiere getMatiere(@PathVariable(name = "id") Long id) {
		return nr.findById(id).get();
	}

	 
	
	@PostMapping("/matiereRestFull/{niv}")
	public ResponseEntity<String> insertMatiere(@RequestBody Matiere n,@PathVariable(name = "niv") Long niv) {
		n.setNiveau(nivRep.getOne(niv));
		nr.save(n);
		return new ResponseEntity<>("saved successefully",HttpStatus.OK);
	}

	@RequestMapping(value = "/matiereRestFull/{id}/{niv}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifieMatiere(@PathVariable(name = "id") Long id,@PathVariable(name = "niv") Long niv, @RequestBody Matiere n) {
		n.setNiveau(nivRep.getOne(niv));
		n.setId(id);
		nr.save(n);
		return new ResponseEntity<>("updated successefully",HttpStatus.OK);
	}

	@DeleteMapping("/matiereRestFull/{id}")
	public ResponseEntity<?> deleteMatiere(@PathVariable Long id) {
		
		nr.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
