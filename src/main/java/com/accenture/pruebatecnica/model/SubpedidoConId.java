package com.accenture.pruebatecnica.model;

public class SubpedidoConId {
	private long idProducto;
	private int cantidad;
	public SubpedidoConId(long idProducto, int cantidad) {
		super();
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
