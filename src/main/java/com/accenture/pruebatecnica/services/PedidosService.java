package com.accenture.pruebatecnica.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.pruebatecnica.model.Pedido;
import com.accenture.pruebatecnica.model.PedidoProducto;
import com.accenture.pruebatecnica.model.Producto;
import com.accenture.pruebatecnica.model.Subpedido;

@Component
public class PedidosService {
	
	@Autowired
	UsuariosService usuariosService;
	@Autowired
	ProductosService productosService;
	
	private static Map<Long, Pedido> pedidos = new HashMap<>();  // idPedido -> Pedido
	private static Map<Long, Pedido> carritos = new HashMap<>(); // idUsuario -> Carrito
	private static List<PedidoProducto> pedidosProductos = new ArrayList<>();
	private static long siguienteID = 1;
	
	public List<Pedido> obtenerPedidosPorUsuario(long idUsuario){
		return pedidos.values()
					.stream()
					.filter((pedido) -> {return pedido.getFkIdUsuario() == idUsuario;})
					.collect(Collectors.toList());
	}
	
	public boolean agregarAlCarrito(long idUsuario, long idProducto, int cantidad) {
		Producto producto = productosService.obtenerPorID(idProducto);
		if (producto == null) { return false; }
		Pedido carrito = obtenerCarrito(idUsuario);
		carrito.addSubpedido(producto, cantidad);
		return true;
	}
	
	public Pedido obtenerCarrito(long idUsuario) {
		Pedido carrito = carritos.get(idUsuario);
		if (carrito == null) {
			long nuevoID = siguienteID++;
			carrito = new Pedido(nuevoID, idUsuario);
			carritos.put(idUsuario, carrito);
		}
		carrito.calcularTotal();
		return carrito;
	}
	
	public boolean completarCarrito(long idUsuario) {
		Pedido carrito = carritos.get(idUsuario);
		if (carrito == null || carrito.vacio()) { return false; }
		carrito.calcularTotal();
		pedidos.put(carrito.getId(), carrito);
		carritos.remove(idUsuario);
		return true;
	}
	
	public List<Subpedido> obtenerProductosPorPedido(long idPedido){
		/*return pedidosProductos.stream()
						.filter((pp) -> {return pp.getFkIdPedido() == idPedido;})
						.map(pp -> productosService.obtenerPorID(pp.getFkIdProducto()))
						.collect(Collectors.toList());*/
		return pedidos.get(idPedido).getSubpedidos();
	}
	
	public double obtenerPrecioCarrito(long idUsuario) {
		Pedido carrito = carritos.get(idUsuario);
		if (carrito == null) { return -1; }
		return carrito.calcularTotal();
	}
}
