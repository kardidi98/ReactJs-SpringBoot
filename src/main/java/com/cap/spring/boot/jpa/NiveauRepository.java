package com.cap.spring.boot.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cap.spring.boot.entities.Niveau;

@RepositoryRestResource
public interface NiveauRepository extends JpaRepository<Niveau, Long>{
	@Query("select n from Niveau n where n.nom like :x")
	public Page<Niveau> findNiveauByNom(@Param("x") String nom, Pageable pageable);

	public Page<Niveau> findByNom(String nom, Pageable pageable);
	public List<Niveau> findByNom(String nom);
}
