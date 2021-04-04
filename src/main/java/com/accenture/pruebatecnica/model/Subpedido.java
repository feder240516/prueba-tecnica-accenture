package com.accenture.pruebatecnica.model;

public class Subpedido {
	private Producto producto;
	private int cantidad;
	private double subtotal;
	private long fkIdProducto;
	
	public Subpedido(Producto producto, int cantidad, double subtotal, long fkIdProducto) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.fkIdProducto = fkIdProducto;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public long getFkIdProducto() {
		return fkIdProducto;
	}
	public void setFkIdProducto(long fkIdProducto) {
		this.fkIdProducto = fkIdProducto;
	}
	public double calcularSubtotal() {
		subtotal = producto.getPrecio() * cantidad;
		return subtotal;
	}
}
