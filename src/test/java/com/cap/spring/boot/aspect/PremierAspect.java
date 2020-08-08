package com.cap.spring.boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PremierAspect {
	
	@Before("execution(* com.cap.spring.boot.jpa.FournisseurRepository.findFournisseurByNom(..))")
	public void getCustomerByIdAdvice() {
		System.out.println("Execution advice sur findFournisseurByNom()");
	}
 
	@Before("execution(* com.cap.spring.boot.entities.Fournisseur.set*(..))")
	public void getAllSetAdvices(JoinPoint joinPoint) {
		System.out.println("Execution advice sur la methode Set de l'entite : " + joinPoint.getSignature().getName());
	}
}