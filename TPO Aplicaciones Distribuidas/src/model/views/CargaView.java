package model.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import model.impl.EstadoCarga;
import model.impl.TipoCarga;

public class CargaView {
	
	private int codigo;
	private Collection<ItemProductoView> productos;
	private TipoCarga tipo;
	private Date fechaMaximaEntrega;
	private Date fechaProbableEntrega;
	private String cliente;
	private String manifiesto;
	private UbicacionView origen;
	private UbicacionView destino;
	private EstadoCarga estadoCarga;
	
	public CargaView(int codigo, TipoCarga tipoCarga, Date fechaMaximaEntrega,
			Date fechaProbableEntrega, String cliente, String manifiesto,
			UbicacionView origen, UbicacionView destino, EstadoCarga estadoCarga) {
		this.codigo = codigo;
		this.tipo = tipoCarga;
		this.fechaMaximaEntrega = fechaMaximaEntrega;
		this.fechaProbableEntrega = fechaProbableEntrega;
		this.cliente = cliente;
		this.manifiesto = manifiesto;
		this.origen = origen;
		this.destino = destino;	
		this.estadoCarga = estadoCarga;
		this.productos = new ArrayList<ItemProductoView>();
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Collection<ItemProductoView> getProductos() {
		return productos;
	}
	public void setProductos(Collection<ItemProductoView> productos) {
		this.productos = productos;
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getManifiesto() {
		return manifiesto;
	}
	public void setManifiesto(String manifiesto) {
		this.manifiesto = manifiesto;
	}
	public UbicacionView getOrigen() {
		return origen;
	}
	public void setOrigen(UbicacionView origen) {
		this.origen = origen;
	}
	public UbicacionView getDestino() {
		return destino;
	}
	public void setDestino(UbicacionView destino) {
		this.destino = destino;
	}
	public EstadoCarga getEstadoCarga() {
		return estadoCarga;
	}
	public void setEstadoCarga(EstadoCarga estadoCarga) {
		this.estadoCarga = estadoCarga;
	}	
}
