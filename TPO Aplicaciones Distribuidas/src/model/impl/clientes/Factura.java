package model.impl.clientes;

import java.io.Serializable;

import model.impl.cargas.Carga;

public class Factura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6357766629462615184L;
	
	private Float monto;
	private Carga carga;
	
	public Factura(Float monto, Carga carga){
		
		this.monto = monto;
		this.carga = carga;
	}
	
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public Carga getCarga() {
		return carga;
	}
	public void setCarga(Carga carga) {
		this.carga = carga;
	}
}
