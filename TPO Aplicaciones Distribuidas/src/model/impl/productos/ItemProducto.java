package model.impl.productos;

import model.impl.PersistentObject;

public class ItemProducto extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6147736562215085115L;
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
