package com.cap.spring.boot.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "matiere")
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
	private Niveau niveau;

	public Matiere() {
		super();
	}

	public Matiere(String nom, boolean specialite) {
		super();
		this.nom = nom;
		this.specialite = specialite;
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
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
}
