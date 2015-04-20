package model.impl;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6178101173670139637L;

	private String codigoUnico;
	private String nombre;
	
	public Cliente(String codigoUnico, String nombre){
		this.codigoUnico = codigoUnico;
		this.nombre = nombre;
	}

	public String getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract void cobrarEnvio(Factura factura);	
			
}
