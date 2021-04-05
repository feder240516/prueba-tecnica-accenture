package com.accenture.pruebatecnica.model;

public class Subpedido {
	private long id;
	private Producto producto;
	private int cantidad;
	private double subtotal;
	private long fkIdProducto;
	
	public Subpedido(long id) {
		super();
		this.id = id;
	}

	/*public Subpedido(int cantidad, long fkIdProducto) {
		super();
		this.cantidad = cantidad;
		this.fkIdProducto = fkIdProducto;
	}*/

	public Subpedido(long id, Producto producto, int cantidad, double subtotal, long fkIdProducto) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.fkIdProducto = fkIdProducto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		Subpedido other = (Subpedido) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
