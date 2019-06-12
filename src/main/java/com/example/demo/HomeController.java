package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHome() {
		return"home";
	}
	
	@GetMapping("/userpage") 
	public String getSecureSauce() {
		return "userPage";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";	
	}
}
