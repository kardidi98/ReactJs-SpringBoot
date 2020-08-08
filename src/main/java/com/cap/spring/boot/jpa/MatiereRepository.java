package com.cap.spring.boot.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.spring.boot.entities.Matiere;


public interface MatiereRepository extends JpaRepository<Matiere, Long>{

}
