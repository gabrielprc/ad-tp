package model.impl;

import java.io.Serializable;
import java.util.Date;

public class Carga implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -875716574330563168L;
	
	private Integer codigo;
	private Producto producto;
	private TipoCarga tipo;
	private Date fechaMaximaEntrega;
	private Date fechaProbableEntrega;
	private Cliente cliente;
	private String manifiesto;
	private Ubicacion origen;
	private Ubicacion destino;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
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
	
}
