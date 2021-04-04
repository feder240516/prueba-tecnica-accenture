package com.accenture.pruebatecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.pruebatecnica.services.UsuariosService;

@Controller
public class LoginController {
	
	@Autowired
	UsuariosService loginService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String mensajeCompra() {
		return "login";
	}
	
	@ResponseBody 
	@PostMapping("/login")
	public String mensajeCompra(@RequestParam String email, @RequestParam String clave, ModelMap model) {
		return loginService.validateUser(email, clave) ? "OK" : "Credenciales invalidas";
	}
	
}
