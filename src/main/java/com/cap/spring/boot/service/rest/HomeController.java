package com.cap.spring.boot.service.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowedHeaders = "*",maxAge = 3600)
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "success";
	}
}
