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

import com.cap.spring.boot.entities.Matiere;
import com.cap.spring.boot.entities.Niveau;
import com.cap.spring.boot.jpa.MatiereRepository;
import com.cap.spring.boot.jpa.NiveauRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowedHeaders = "*",maxAge = 3600)
public class MatiereRestService {

	@Autowired
	MatiereRepository nr;

	@RequestMapping(value = "/matiereRestFull", method = RequestMethod.GET)
	public List<Matiere> listMatieres() {
		return nr.findAll();
	}

	@RequestMapping(value = "/matiereRestFull/{id}", method = RequestMethod.GET)
	public Matiere getMatiere(@PathVariable(name = "id") Long id) {
		return nr.findById(id).get();
	}

	 
	
	@PostMapping("/matiereRestFull")
	public ResponseEntity<String> insertMatiere(@RequestBody Matiere n) {
		System.out.println(n.toString());
		
		nr.save(n);
		return new ResponseEntity<>("saved successefully",HttpStatus.OK);
	}

	@RequestMapping(value = "/matiereRestFull/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifieMatiere(@PathVariable(name = "id") Long id, @RequestBody Matiere n) {
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
