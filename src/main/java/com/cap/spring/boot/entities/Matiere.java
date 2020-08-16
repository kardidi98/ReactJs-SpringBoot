package com.cap.spring.boot.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "matiere")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Matiere implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549253437911549642L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 3, max = 50)
	private String nom;
	private boolean specialite;
	@ManyToOne
	@JsonDeserialize
	private Niveau level;


	public Matiere() {
	}

	public Matiere(String nom, boolean specialite) {
		super();
		this.nom = nom;
		this.specialite = specialite;
	}
	public Matiere(@Size(min = 3, max = 50) String nom, boolean specialite, Niveau niveau) {
		this.nom = nom;
		this.specialite = specialite;
		this.level = niveau;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isSpecialite() {
		return specialite;
	}

	public void setSpecialite(boolean specialite) {
		this.specialite = specialite;
	}

	public Niveau getNiveau() {
		return level;
	}

	public void setNiveau(Niveau niveau) {
		this.level = niveau;
	}
}
