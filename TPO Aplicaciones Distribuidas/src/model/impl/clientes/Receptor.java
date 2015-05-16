package model.impl.clientes;

import model.impl.PersistentObject;
import model.impl.misc.Ubicacion;

public class Receptor extends PersistentObject  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5143126364253623009L;
	private String dni;
	private String nombre;
	private String apellido;
	private Ubicacion ubicacion;
	
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
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	

}
