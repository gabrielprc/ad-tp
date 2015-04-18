package model.impl;

import java.io.Serializable;

public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3613165521778210794L;
	
	private String cuit;
	private String dni;
	private String nombre;
	private String apellido;
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
