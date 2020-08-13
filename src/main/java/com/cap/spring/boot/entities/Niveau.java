package com.cap.spring.boot.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "niveau")
public class Niveau implements Serializable, Comparable<Niveau>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4154151020989087904L;
	@Id
	@GeneratedValue
	private Long id;
	@Size(min = 3, max = 50)
	private String nom;
	private String abr;
	@OneToMany(mappedBy = "niveau")
	@JsonIgnore
	private List<Matiere> matieres=new ArrayList<Matiere>();

	public Niveau() {
		super();
	}

	public Niveau(Long id, String nom, String abr) {
		super();
		this.id = id;
		this.nom = nom;
		this.abr = abr;
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

	public String getAbr() {
		return abr;
	}

	public void setAbr(String abr) {
		this.abr = abr;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	@Override
	public int compareTo(Niveau o) {
		return nom.compareTo(o.getNom());
	}

}
