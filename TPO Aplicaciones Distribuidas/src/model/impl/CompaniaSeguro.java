package model.impl;

import java.io.Serializable;
import java.util.List;

public class CompaniaSeguro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7546501742115547665L;
	
	private String cuil;
	private String nombre;
	private List<Seguro> seguros;
	
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Seguro> getSeguros() {
		return seguros;
	}
	public void setSeguros(List<Seguro> seguros) {
		this.seguros = seguros;
	}
	
}
