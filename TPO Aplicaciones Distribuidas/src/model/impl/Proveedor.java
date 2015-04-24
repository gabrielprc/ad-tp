package model.impl;

import java.io.Serializable;

public class Proveedor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4550038024972428465L;

	private String cuit;
	private String nombre;
	
	public Proveedor(String cuit, String nombre){
		
		this.cuit = cuit;
		this.nombre = nombre;
		
	}
	
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}
