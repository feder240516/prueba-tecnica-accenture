package com.accenture.pruebatecnica.services;

import java.time.Instant;
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
import com.accenture.pruebatecnica.model.SubpedidoConId;
import com.accenture.pruebatecnica.model.Usuario;

@Component
public class PedidosService {
	
	@Autowired
	UsuariosService usuariosService;
	@Autowired
	ProductosService productosService;
	
	private static Map<Long, Pedido> pedidos = new HashMap<>();  // idPedido -> Pedido
	private static Map<Long, Pedido> carritos = new HashMap<>(); // idUsuario -> Carrito
	private static long siguienteID = 1;
	private static long siguienteIDSubpedido = 1;
	private final static long CINCO_HORAS = 5 * 24 * 60 * 60 * 1000;
	private final static long DOCE_HORAS = 12 * 24 * 60 * 60 * 1000;
	
	public List<Pedido> obtenerPedidosPorUsuario(long idUsuario){
		return pedidos.values()
					.stream()
					.filter((pedido) -> {return pedido.getFkIdUsuario() == idUsuario;})
					.collect(Collectors.toList());
	}
	
	public Pedido obtenerPedido(long idUsuario, long idPedido) {
		return pedidos.get(idPedido);
	}
	
	public boolean agregarAlCarrito(long idUsuario, long idProducto, int cantidad) {
		Producto producto = productosService.obtenerPorID(idProducto);
		if (producto == null || cantidad == 0) { return false; }
		Pedido carrito = obtenerCarrito(idUsuario);
		long idSubpedido = siguienteIDSubpedido++;
		carrito.addSubpedido(idSubpedido, producto, cantidad);
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
		Usuario usuario = usuariosService.obtenerPorId(idUsuario);
		if (carrito == null || usuario == null || carrito.vacio()) { return false; }
		double saldoTotal = carrito.calcularTotal();
		boolean exito = usuario.sumarSaldo(-saldoTotal);
		if (!exito) return false;
		carrito.calcularFecha();
		pedidos.put(carrito.getId(), carrito);
		carritos.remove(idUsuario);
		return true;
	}
	
	public boolean eliminarSubpedido(long idUsuario, long idSubpedido) {
		Pedido carrito = carritos.get(idUsuario);
		if (carrito == null || carrito.vacio()) { return false; }
		return carrito.eliminarSubpedido(idSubpedido);
	}
	
	public boolean modificarPedido(long idPedido, List<SubpedidoConId> subpedidos) {
		Pedido pedidoViejo = pedidos.get(idPedido);
		Usuario usuario = usuariosService.obtenerPorId(pedidoViejo.getFkIdUsuario());
		if(pedidoViejo == null || usuario == null) { return false; }
		long ahoraMilis = Instant.now().toEpochMilli();
		if (ahoraMilis - pedidoViejo.getFechaCreacion() > CINCO_HORAS) { return false; }
		Pedido pedido = new Pedido(0,0);
		subpedidos.forEach(subpedidoid -> {
			Producto producto = productosService.obtenerPorID(subpedidoid.getIdProducto());
			long siguienteIdSubpedido = siguienteIDSubpedido++;
			pedido.addSubpedido(siguienteIdSubpedido, producto, subpedidoid.getCantidad());
		});
		pedido.calcularTotal();
		System.out.println(String.format("Pedido viejo: %f\nPedido nuevo: %f", pedidoViejo.getPrecioBruto(), pedido.getPrecioBruto()));
		
		if (pedido.getPrecioBruto() < pedidoViejo.getPrecioBruto()) {
			return false;
		}
		
		if (!usuario.sumarSaldo(pedido.getPrecioFinal() - pedidoViejo.getPrecioFinal())) { return false; }
		
		pedidoViejo.setSubpedidos(pedido.getSubpedidos());
		return true;
	}
	
	public boolean eliminarPedido(long idPedido, long idUsuario) {
		Pedido pedido = pedidos.get(idPedido);
		Usuario usuario = usuariosService.obtenerPorId(idUsuario);
		if (pedido == null || usuario == null || pedido.getFkIdUsuario() != idUsuario) { return false; }
		long ahoraMilis = Instant.now().toEpochMilli();
		if (ahoraMilis - pedido.getFechaCreacion() > DOCE_HORAS) { 
			usuario.sumarSaldo(pedido.calcularTotal() * 0.9);
		} else {
			usuario.sumarSaldo(pedido.calcularTotal());
		}
		pedidos.remove(pedido);
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
