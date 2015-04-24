package model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Carga implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -875716574330563168L;

	private Integer codigo;
	private Collection<ItemProducto> productos;
	private TipoCarga tipo;
	private Date fechaMaximaEntrega;
	private Date fechaProbableEntrega;
	private Cliente cliente;
	private String manifiesto;
	private Ubicacion origen;
	private Ubicacion destino;
	private EstadoCarga estadoCarga;

	public Carga(int codigo, TipoCarga tipoCarga, Date fechaMaximaEntrega,
			Date fechaProbableEntrega, Cliente cliente, String manifiesto,
			Ubicacion origen, Ubicacion destino, EstadoCarga estadoCarga) {
		this.codigo = codigo;
		this.tipo = tipoCarga;
		this.fechaMaximaEntrega = fechaMaximaEntrega;
		this.fechaProbableEntrega = fechaProbableEntrega;
		this.cliente = cliente;
		this.manifiesto = manifiesto;
		this.origen = origen;
		this.destino = destino;
		this.estadoCarga = estadoCarga;
		this.productos = new ArrayList<ItemProducto>();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public TipoCarga getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarga tipo) {
		this.tipo = tipo;
	}

	public Date getFechaMaximaEntrega() {
		return fechaMaximaEntrega;
	}

	public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
		this.fechaMaximaEntrega = fechaMaximaEntrega;
	}

	public Date getFechaProbableEntrega() {
		return fechaProbableEntrega;
	}

	public void setFechaProbableEntrega(Date fechaProbableEntrega) {
		this.fechaProbableEntrega = fechaProbableEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getManifiesto() {
		return manifiesto;
	}

	public void setManifiesto(String manifiesto) {
		this.manifiesto = manifiesto;
	}

	public Ubicacion getOrigen() {
		return origen;
	}

	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}

	public Collection<ItemProducto> getProductos() {
		return productos;
	}

	public void setProductos(Collection<ItemProducto> productos) {
		this.productos = productos;
	}

	public float calcularCosto() {
		return 0;
	}

	public float calcularVolumenTotal() {

		float volumen = 0;

		for (ItemProducto p : productos)
			volumen += p.calcularPesoParcial();

		return volumen;
	}

	public float calcularPesoTotal() {

		float peso = 0;

		for (ItemProducto p : productos) {
			peso += p.calcularPesoParcial();
		}
		return peso;
	}

	public EstadoCarga getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(EstadoCarga estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public void agregarItemProducto(Producto producto, float cantidad) {
		productos.add(new ItemProducto(producto, cantidad));		
	}

}
