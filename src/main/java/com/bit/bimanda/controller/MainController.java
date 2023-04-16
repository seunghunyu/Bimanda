package com.bit.bimanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.bimanda.service.UserService;

@Controller
public class MainController {
	@Autowired
	UserService UserServiceImpl;
	@RequestMapping("/")
	public String main() {
		return "home";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
}
