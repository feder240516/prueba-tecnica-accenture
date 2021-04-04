package com.accenture.pruebatecnica.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.accenture.pruebatecnica.model.Producto;

@Service
public class ProductosService {
	
	private static Map<Long,Producto> productos = new HashMap<>();
	
	static {
		productos.put(1L,new Producto(1,"Camisa","Para hombre y mujer", 25000));
		productos.put(2L,new Producto(2,"Zapatos","Para salir a correr", 45000));
		productos.put(3L,new Producto(3,"Gorra","Con estilo", 32000));
		productos.put(4L,new Producto(4,"Pantalón","Resistente a la lavadora", 62000));
	}
	
	public Collection<Producto> obtenerTodos(){
		return productos.values();
	}
	
	public Producto obtenerPorID(long id) {
		return productos.get(id);
	}
}
