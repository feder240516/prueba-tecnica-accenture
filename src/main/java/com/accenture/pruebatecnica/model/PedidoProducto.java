package com.accenture.pruebatecnica.model;

public class PedidoProducto {
	private long fkIdPedido;
	private long fkIdProducto;
	
	public long getFkIdPedido() {
		return fkIdPedido;
	}
	public void setFkIdPedido(long fkIdPedido) {
		this.fkIdPedido = fkIdPedido;
	}
	public long getFkIdProducto() {
		return fkIdProducto;
	}
	public void setFkIdProducto(long fkIdProducto) {
		this.fkIdProducto = fkIdProducto;
	}
}
