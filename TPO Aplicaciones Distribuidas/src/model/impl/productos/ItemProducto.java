package model.impl.productos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.impl.PersistentObject;

@Entity
@Table(name = "ItemsProducto")
public class ItemProducto extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6147736562215085115L;
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;
	@Column(name = "cantidad")
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
