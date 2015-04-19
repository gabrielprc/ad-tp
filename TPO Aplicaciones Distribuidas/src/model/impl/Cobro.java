package model.impl;

import java.io.Serializable;
import java.util.List;

public class Cobro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6031414221082912157L;
	
	private Factura factura;
	private List<CobroParcial> cobrosParciales;
	
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public List<CobroParcial> getCobrosParciales() {
		return cobrosParciales;
	}
	public void setCobrosParciales(List<CobroParcial> cobrosParciales) {
		this.cobrosParciales = cobrosParciales;
	}
	
}
