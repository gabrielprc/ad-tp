package model.impl;

public class ItemProducto {

	private Producto producto;
	private float cantidad;

	public ItemProducto(Producto producto, float cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float calcularVolumenParcial() {
		return producto.getTamano().calcularVolumen() * cantidad;
	}
	
	public float calcularPesoParcial(){
		return producto.getPeso() * cantidad;
	}
	
}
