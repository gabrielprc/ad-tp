package model.impl;

import java.io.Serializable;
import java.util.Date;

public class Pago implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6122982765032430365L;
	
	private Proveedor proveedor;
	private Float monto;
	private Date fecha;
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
