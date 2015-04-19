package model.impl;

import java.io.Serializable;
import java.util.Date;

public class CobroParcial implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1811141989154565682L;
	
	private Date fecha;
	private Float monto;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
}
