package com.Yadeedya.Yadeedya_social_media.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping
	public String homeControllerHandler() {
		return "starting my project";
		
	}
	
}
