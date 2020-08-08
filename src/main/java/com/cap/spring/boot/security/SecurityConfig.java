package com.cap.spring.boot.security;


import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("kabbaj").password("{noop}1234").roles("Chef_Departement", "User");
		auth.inMemoryAuthentication().withUser("issam").password("{noop}1234").roles("Responsable_Filiere", "User");
		auth.inMemoryAuthentication().withUser("mohammed").password("{noop}1234").roles("User");
		
//		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("kabbaj").password(password).usersByUsernameQuery("select username as principal, password as credentials, active from users where username = ?").authoritiesByUsernameQuery("select username as principal, role as role from user_role where username=?").rolePrefix("ROLE_").passwordEncoder(new Pbkdf2PasswordEncoder());
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "PUT", "POST",
		"DELETE").allowedHeaders("*");
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/components/**","/*.js").permitAll();
		http.authorizeRequests().antMatchers("/niveauRestFull/**").permitAll();
		http.cors()
		.and()
		.csrf()
		.disable()
		.headers()
		.frameOptions()
		.deny();
		http.authorizeRequests().antMatchers("/niveaux").hasAnyRole("Directeur");
		http.authorizeRequests().antMatchers("/niveaux").hasAnyRole("Chef_Departement");
		http.authorizeRequests().antMatchers("/matieres").hasAnyRole("User");
		http.authorizeRequests().antMatchers("/chercherNiveau").authenticated();
		http.authorizeRequests().antMatchers("/ajouterMatieres").hasRole("Responsable_Filiere");		
		http.authorizeRequests().antMatchers("/ajouterMatieres").hasAuthority("User");
		http.authorizeRequests().antMatchers("/matieres").hasAuthority("User");
		http.exceptionHandling().accessDeniedPage("/403");	
	}
	


}
