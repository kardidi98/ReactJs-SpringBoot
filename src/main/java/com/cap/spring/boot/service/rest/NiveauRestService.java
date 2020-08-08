package com.cap.spring.boot.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cap.spring.boot.entities.Niveau;
import com.cap.spring.boot.jpa.NiveauRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowedHeaders = "*",maxAge = 3600)
public class NiveauRestService {

	@Autowired
	NiveauRepository nr;

	@RequestMapping(value = "/niveauRestFull", method = RequestMethod.GET)
	public List<Niveau> listNiveaux() {
		return nr.findAll();
	}

	@RequestMapping(value = "/niveauRestFull/{id}", method = RequestMethod.GET)
	public Niveau getNiveau(@PathVariable(name = "id") Long id) {
		return nr.findById(id).get();
	}

	 
	
	@PostMapping("/niveauRestFull")
	public ResponseEntity<String> insertNiveau(@RequestBody Niveau n) {
		System.out.println(n.toString());
		
		nr.save(n);
		return new ResponseEntity<>("saved successefully",HttpStatus.OK);
	}

	@RequestMapping(value = "/niveauRestFull/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifierFournisseur(@PathVariable(name = "id") Long id, @RequestBody Niveau n) {
		n.setId(id);
		nr.save(n);
		return new ResponseEntity<>("updated successefully",HttpStatus.OK);
	}

	@DeleteMapping("/niveauRestFull/{id}")
	public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
		
		nr.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
