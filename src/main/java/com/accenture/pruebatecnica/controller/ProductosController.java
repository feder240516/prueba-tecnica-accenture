package com.accenture.pruebatecnica.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.pruebatecnica.model.Producto;
import com.accenture.pruebatecnica.services.ProductosService;

@CrossOrigin
@RestController
public class ProductosController {
	@Autowired
	ProductosService productosService;
	
	@GetMapping("/productos")
	public Collection<Producto> obtenerTodos() {
		return productosService.obtenerTodos();
	}
}
