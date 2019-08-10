package com.accolite.au;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AUController {
	@RequestMapping("/hello")
	public String  redirect() {
		return "hello";
	}
	
	
	
	@RequestMapping("/asd")
	public String file() {
		return "file";
	}
	
	
	
	
}
