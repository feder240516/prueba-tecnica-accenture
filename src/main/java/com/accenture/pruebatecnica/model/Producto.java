package com.accenture.pruebatecnica.model;

public class Producto {
	private long id;
	private String nombre;
	private String descripción;
	private double precio;
	public Producto(long id, String nombre, String descripción, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripción = descripción;
		this.precio = precio;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
