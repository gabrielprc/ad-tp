package model.impl;

public class ItemProducto {

	private Producto producto;
	private int cantidad;

	public ItemProducto(Producto producto, int cantidad) {

		this.setProducto(producto);
		this.setCantidad(cantidad);
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

	public float calcularVolumenParcial() {
		return producto.getTamano().calcularVolumen() * cantidad;
	}
	
	public float calcularPesoParcial(){
		return producto.getPeso() * cantidad;
	}
	
}
