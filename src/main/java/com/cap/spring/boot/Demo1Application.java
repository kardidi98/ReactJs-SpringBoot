package com.cap.spring.boot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cap.spring.boot.entities.Niveau;
import com.cap.spring.boot.entities.Matiere;
import com.cap.spring.boot.jpa.MatiereRepository;
import com.cap.spring.boot.jpa.NiveauRepository;

@SpringBootApplication
public class Demo1Application implements CommandLineRunner{
	
	@Autowired
	NiveauRepository nr;

	@Autowired
	MatiereRepository mr;
	
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		
	}
	
	
	
	@Override
	public void run(String... arg0) throws Exception {

		nr.save(new Niveau(1L, "1 annee genie info", "1ginf"));
		nr.save(new Niveau(2L, "2 annee genie info", "2ginf"));
		nr.save(new Niveau(3L, "3 annee genie info", "3ginf"));
		nr.save(new Niveau(4L, "1 annee genie industriel", "1gind"));
		nr.save(new Niveau(5L, "2 annee genie industriel", "2gind"));
		nr.save(new Niveau(6L, "3 annee genie industriel", "3gind"));


		Matiere jee = new Matiere("JEE", true);
		jee.setNiveau(nr.findById(1L).get());
		mr.save(jee);
		
	}
}
