package com.accenture.pruebatecnica.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private long id;
	private long fechaCreacion;
	private boolean completado;
	private double precioBruto;
	private boolean tieneRecargoDomicilio;
	private double precioFinal;
	private List<Subpedido> subpedidos;
	private long fkIdUsuario;
	
	public Pedido(long id, long fkIdUsuario) {
		super();
		this.id = id;
		this.fkIdUsuario = fkIdUsuario;

		this.completado = false;
		this.precioBruto = 0;
		this.fechaCreacion = 1;
		this.subpedidos = new ArrayList<Subpedido>();
		this.tieneRecargoDomicilio = false;
		this.precioFinal = 0;
	}

	public Pedido(long id, long fechaCreacion, boolean completado, double precioBruto, boolean tieneIva, boolean tieneRecargoDomicilio, long fkIdUsuario) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.completado = completado;
		this.precioBruto = precioBruto;
		this.fkIdUsuario = fkIdUsuario;
		
		this.subpedidos = new ArrayList<Subpedido>();
		this.tieneRecargoDomicilio = false;
		this.precioFinal = 0;
		
	}
	
	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	public boolean isTieneRecargoDomicilio() {
		return tieneRecargoDomicilio;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public List<Subpedido> getSubpedidos() {
		return subpedidos;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(long fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isCancelado() {
		return completado;
	}
	public void setCancelado(boolean cancelado) {
		this.completado = cancelado;
	}
	
	public double getPrecioBruto() {
		return precioBruto;
	}
	
	public long getFkIdUsuario() {
		return fkIdUsuario;
	}
	
	public void setFkIdUsuario(long fkIdUsuario) {
		this.fkIdUsuario = fkIdUsuario;
	}
	
	public void addSubpedido(Producto producto, int cantidad) {
		this.subpedidos.add(new Subpedido(producto,cantidad,0,producto.getId()));
	}
	
	public void addSubpedido(Subpedido subpedido) {
		this.subpedidos.add(subpedido);
	}
	
	public double calcularTotal() {
		precioBruto = 0;
		for(Subpedido subpedido: subpedidos) {
			precioBruto += subpedido.calcularSubtotal();
		}
		tieneRecargoDomicilio = precioBruto < 100000 && precioBruto > 0;
		precioFinal = precioBruto * 1.19 + (tieneRecargoDomicilio? 10000: 0);
		return precioFinal;
	}
	
	public boolean vacio() {
		return subpedidos.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
