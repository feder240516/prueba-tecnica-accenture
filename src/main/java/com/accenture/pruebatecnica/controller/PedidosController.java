package com.accenture.pruebatecnica.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.pruebatecnica.model.Pedido;
import com.accenture.pruebatecnica.model.Subpedido;
import com.accenture.pruebatecnica.services.PedidosService;

@RestController
public class PedidosController {
	
	@Autowired
	PedidosService pedidosService;

	@GetMapping("/pedidos/{idUsuario}")
	public List<Pedido> obtenerPedidosPorUsuario(@PathVariable long idUsuario) {
		return pedidosService.obtenerPedidosPorUsuario(idUsuario);
		/*URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(42).toUri();
		return ResponseEntity.created(location).build();*/
	}
	
	/*@PostMapping("/pedidos")
	public ResponseEntity<Void> crearPedido(@RequestBody Pedido pedido){
		long nuevoID = pedidosService.crearPedido(pedido);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(nuevoID).toUri();
		return ResponseEntity.created(location).build();
	}*/
	
	@GetMapping("/carrito/{idUsuario}")
	public Pedido obtenerCarrito(@PathVariable long idUsuario) {
		return pedidosService.obtenerCarrito(idUsuario);
	}
	
	@PutMapping("/carrito/{idUsuario}")
	public ResponseEntity<Void> agregarProductoACarrito(@PathVariable long idUsuario, @RequestBody Map<String, Object> request){
		System.out.println(request);
		long idProducto = (int)request.get("idProducto");
		int cantidad = (int)request.get("cantidad");
		
		boolean ok = pedidosService.agregarAlCarrito(idUsuario, idProducto, cantidad);
		if (ok) return ResponseEntity.ok().build();
		else return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/carrito/{idUsuario}/completar")
	public ResponseEntity<Void> completarCarrito(@PathVariable long idUsuario){
		boolean exito = pedidosService.completarCarrito(idUsuario);
		if (exito) return ResponseEntity.ok().build();
		else return ResponseEntity.badRequest().build();
	}
	
	/*@RequestMapping(value="/pedido", method=RequestMethod.POST)
	public String mensajeCompra(@RequestParam String email, @RequestParam String password, ModelMap model) {
		model.put("email", email);
		model.put("password", password);
		return "resultadoCompra";
	}*/
}
