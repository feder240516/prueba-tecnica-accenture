package com.accenture.pruebatecnica.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.pruebatecnica.services.UsuariosService;

@CrossOrigin
@RestController
public class LoginController {
	
	@Autowired
	UsuariosService loginService;
	
	//@ResponseBody 
	@PostMapping("/login")
	public ResponseEntity<Object> mensajeCompra(@RequestBody Map<String, Object> request) {
		String email = (String) request.get("email");
		String clave = (String) request.get("clave");
		System.out.println(String.format("%s -> %s", email,clave));
		long userID = loginService.validateUser(email, clave);
		if (userID == -1) {
			return ResponseEntity.badRequest().build(); 
		} else {
			Map<String,Object> json = new HashMap<>();
			json.put("userID", userID);
			return ResponseEntity.ok(json);
		}
	}
	
}
